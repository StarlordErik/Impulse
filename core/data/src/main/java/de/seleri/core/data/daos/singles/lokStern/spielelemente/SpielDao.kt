package de.seleri.core.data.daos.singles.lokStern.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.daos.joins.JoinDao
import de.seleri.core.data.entities.joins.SpielXKategorieRoom
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.lokStern.spielelemente.SpielRoom

@Dao
interface SpielDao: SpielelementDao<SpielRoom>, JoinDao<SpielXKategorieRoom> {

	@Upsert
	override suspend fun upsert(entity: SpielRoom): Long

	@Delete
	override suspend fun delete(entity: SpielRoom)

	@Query("SELECT * FROM Spiele WHERE id = :spielelementID")
	override suspend fun get(spielelementID: Int): SpielRoom

	@Query("SELECT * FROM Spiele")
	suspend fun getAll(): List<SpielRoom>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	override suspend fun insert(joinEntity: SpielXKategorieRoom)

	@Delete
	override suspend fun delete(joinEntity: SpielXKategorieRoom)

	@Query("SELECT * FROM SpielXKategorie WHERE spielID = :sammlungsId")
	override suspend fun getAllConnections(sammlungsId: Int): List<SpielXKategorieRoom>

	@Query(
		"""
        SELECT k.* FROM Kategorien k
        INNER JOIN SpielXKategorie x ON k.id = x.kategorieId
        WHERE x.spielId = :spielId
    """
	)
	suspend fun getKategorien(spielId: Int): List<KategorieRoom>

}
