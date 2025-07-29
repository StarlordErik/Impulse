package de.seleri.core.data.daos.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.daos.JoinDao
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom

@Dao
interface SpielDao: SpielelementDao<SpielRoom>, JoinDao<SpielXKategorie> {

	@Upsert
	override suspend fun upsert(entity: SpielRoom): Long

	@Delete
	override suspend fun delete(entity: SpielRoom)

	@Query("SELECT * FROM Spiele WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): SpielRoom

	@Query("SELECT * FROM Spiele")
	suspend fun getAll(): List<SpielRoom>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	override suspend fun insert(joinEntity: SpielXKategorie)

	@Delete
	override suspend fun delete(joinEntity: SpielXKategorie)

	@Query("SELECT * FROM SpielXKategorie WHERE spielID = :sammlungsId")
	override suspend fun getAllConnections(sammlungsId: Int): List<SpielXKategorie>

	@Query(
		"""
        SELECT k.* FROM Kategorien k
        INNER JOIN SpielXKategorie x ON k.id = x.kategorieId
        WHERE x.spielId = :spielId
    """
	)
	suspend fun getKategorien(spielId: Int): List<KategorieRoom>

}
