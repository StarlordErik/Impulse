package de.seleri.core.data.daos.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.daos.JoinDao
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity

@Dao
interface KategorieDao : SpielelementDao<KategorieEntity>, JoinDao<KategorieXKartentext> {

	@Upsert
	override suspend fun upsert(entity: KategorieEntity): Long

	@Delete
	override suspend fun delete(entity: KategorieEntity)

	@Query("SELECT * FROM Kategorien WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): KategorieEntity

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	override suspend fun insert(joinEntity: KategorieXKartentext)

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	override suspend fun insert(joinEntities: Collection<KategorieXKartentext>)

	@Delete
	override suspend fun delete(joinEntity: KategorieXKartentext)

	@Query(
		"""
        SELECT t.* FROM Kartentexte t
        INNER JOIN KategorieXKartentext x ON t.id = x.kartentextID
        WHERE x.kategorieID = :kategorieId
    """
	)
	suspend fun getKartentexte(kategorieId: Int): List<KartentextEntity>
}
