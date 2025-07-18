package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import de.seleri.kern.daten.entities.Spiel
import de.seleri.kern.daten.relationen.SpielMitKategorien

@Dao
interface SpielDao {

  @Upsert
  suspend fun upsert(spiel: Spiel)

  @Delete
  suspend fun delete(spiel: Spiel)

  @Query("SELECT * FROM Spiele WHERE id = :id")
  suspend fun getByID(id: Int): Spiel?

  @Query("SELECT * FROM Spiele")
  suspend fun getAlle(): List<Spiel>

  @Query("SELECT * FROM Spiele WHERE inaktiv = 0")
  suspend fun getAktive(): List<Spiel>

  @Transaction
  @Query("SELECT * FROM Spiele WHERE id = :spielId")
  fun getMitKategorien(spielId: Int): SpielMitKategorien

  @Transaction
  @Query(
    """
  SELECT s.* FROM Spiele s
  INNER JOIN SpielXKategorie x ON s.id = x.spielID
  INNER JOIN Kategorien k ON x.kategorieID = k.id
  WHERE s.id = :spielId AND s.inaktiv = 0 AND k.inaktiv = 0
"""
  )
  fun getMitAktivenKategorien(spielId: Int): SpielMitKategorien
}
