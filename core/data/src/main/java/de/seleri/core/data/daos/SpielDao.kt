package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.data.entities.singles.SpielEntity

@Dao
interface SpielDao : SpielelementDao<SpielEntity> {

	@Upsert
	override suspend fun upsert(entity: SpielEntity): Long

	@Delete
	override suspend fun delete(entity: SpielEntity)

	@Query("SELECT * FROM Spiele WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): SpielEntity

	@Query("SELECT * FROM Spiele")
	suspend fun getAll(): List<SpielEntity>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(spielXKategorie: SpielXKategorie)

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
