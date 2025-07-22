package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity

@Dao
interface KategorieDao : SpielelementDao<KategorieEntity> {

	@Upsert
	override suspend fun upsert(entity: KategorieEntity): Long

	@Delete
	override suspend fun delete(entity: KategorieEntity)

	@Query("SELECT * FROM Kategorien WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): KategorieEntity

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(kategorieXKartentext: KategorieXKartentext)

	@Delete
	suspend fun delete(kategorieXKartentext: KategorieXKartentext)

	@Query(
		"""
        SELECT t.* FROM Kartentexte t
        INNER JOIN KategorieXKartentext x ON t.id = x.kartentextID
        WHERE x.kategorieID = :kategorieId
    """
	)
	suspend fun getKartentexte(kategorieId: Int): List<KartentextEntity>
}
