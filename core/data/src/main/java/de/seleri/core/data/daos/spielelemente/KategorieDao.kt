package de.seleri.core.data.daos.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.daos.JoinDao
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom

@Dao
interface KategorieDao: SpielelementDao<KategorieRoom>, JoinDao<KategorieXKartentextRoom> {

	@Upsert
	override suspend fun upsert(entity: KategorieRoom): Long

	@Delete
	override suspend fun delete(entity: KategorieRoom)

	@Query("SELECT * FROM Kategorien WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): KategorieRoom

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	override suspend fun insert(joinEntity: KategorieXKartentextRoom)

	@Delete
	override suspend fun delete(joinEntity: KategorieXKartentextRoom)

	@Query("SELECT * FROM KategorieXKartentext WHERE kategorieID = :sammlungsId")
	override suspend fun getAllConnections(sammlungsId: Int): List<KategorieXKartentextRoom>

	@Query(
		"""
        SELECT t.* FROM Kartentexte t
        INNER JOIN KategorieXKartentext x ON t.id = x.kartentextID
        WHERE x.kategorieID = :kategorieId
    """
	)
	suspend fun getKartentexte(kategorieId: Int): List<KartentextRoom>
}
