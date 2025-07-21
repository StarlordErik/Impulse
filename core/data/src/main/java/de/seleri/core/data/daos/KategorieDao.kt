package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.singles.KategorieEntity
import de.seleri.core.data.relations.KategorieMitKartentexten

@Dao
interface KategorieDao {

	@Upsert
	suspend fun upsert(kategorie: KategorieEntity): Long

	@Delete
	suspend fun delete(kategorie: KategorieEntity)

	@Transaction
	@Query("SELECT * FROM Kategorien WHERE id = :kategorieId")
	fun getMitKartentexten(kategorieId: Int): KategorieMitKartentexten

	@Upsert
	suspend fun upsert(kategorieXKartentext: KategorieXKartentext)

	@Delete
	suspend fun delete(kategorieXKartentext: KategorieXKartentext)

}
