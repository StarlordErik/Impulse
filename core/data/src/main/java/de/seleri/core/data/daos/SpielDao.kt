package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.SpielEntity
import de.seleri.core.data.relations.SpielMitKategorien

@Dao
interface SpielDao {

	@Upsert
	suspend fun upsert(spiel: SpielEntity)

	@Delete
	suspend fun delete(spiel: SpielEntity)

	@Query("SELECT * FROM Spiele")
	suspend fun getAll(): List<SpielEntity>

	@Transaction
	@Query("SELECT * FROM Spiele WHERE id = :spielId")
	fun getMitKategorien(spielId: Int): SpielMitKategorien

	@Upsert
	suspend fun upsert(spielXKategorie: SpielXKategorie)

	@Delete
	suspend fun delete(spielXKategorie: SpielXKategorie)

}
