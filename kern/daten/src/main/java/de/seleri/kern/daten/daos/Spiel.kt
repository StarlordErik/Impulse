package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.kern.daten.entities.Spiel

@Dao
interface Spiel {

  @Upsert
  suspend fun upsert(spiel: Spiel)

  @Delete
  suspend fun delete(spiel: Spiel)

  @Query("SELECT * FROM Spiele WHERE id = :id")
  suspend fun getByID(id: Int): Spiel?

  @Query("SELECT * FROM Spiele")
  suspend fun getAlle(): List<Spiel>

  @Query("SELECT * FROM Spiele WHERE ausgeblendet = 0")
  suspend fun getAlleSichtbaren(): List<Spiel>
}
