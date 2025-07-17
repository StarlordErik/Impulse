package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.kern.daten.entities.Kategorie

@Dao
interface KategorieDao {

  @Upsert
  suspend fun upsert(kategorie: Kategorie)

  @Delete
  suspend fun delete(kategorie: Kategorie)

  @Query("SELECT * FROM Kategorien WHERE id = :id")
  suspend fun getByID(id: Int): Kategorie?

  @Query("SELECT * FROM Kategorien")
  suspend fun getAlle(): List<Kategorie>

  @Query("SELECT * FROM Kategorien WHERE inaktiv = 0")
  suspend fun getAktive(): List<Kategorie>
}
