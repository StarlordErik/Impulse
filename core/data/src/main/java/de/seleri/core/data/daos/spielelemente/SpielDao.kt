package de.seleri.core.data.daos.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.daos.JoinDao
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielEntityRoom

@Dao
interface SpielDao : SpielelementDao<SpielEntityRoom>, JoinDao<SpielXKategorie> {

	@Upsert
	override suspend fun upsert(entity: SpielEntityRoom): Long

	@Delete
	override suspend fun delete(entity: SpielEntityRoom)

	@Query("SELECT * FROM Spiele WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): SpielEntityRoom

	@Query("SELECT * FROM Spiele")
	suspend fun getAll(): List<SpielEntityRoom>

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
	suspend fun getKategorien(spielId: Int): List<KategorieEntityRoom>

}
