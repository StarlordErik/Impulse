package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.KategorieEntity
import de.seleri.core.data.entities.singles.SpielEntity

@Dao
interface SpielDao {

	@Upsert
	suspend fun upsert(spiel: SpielEntity): Long

	@Delete
	suspend fun delete(spiel: SpielEntity)

	@Query("SELECT * FROM Spiele WHERE id = :spielId")
	suspend fun get(spielId: Int): SpielEntity

	@Query("SELECT * FROM Spiele")
	suspend fun getAll(): List<SpielEntity>

	@Upsert
	suspend fun upsert(spielXKategorie: SpielXKategorie)

	@Delete
	suspend fun delete(spielXKategorie: SpielXKategorie)

	@Query(
		"""
        SELECT k.* FROM Kategorien k
        INNER JOIN SpielXKategorie x ON k.id = x.kategorieId
        WHERE x.spielId = :spielId
    """
	)
	suspend fun getKategorien(spielId: Int): List<KategorieEntity>

}
