package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.kern.daten.entities.Kartentext

@Dao
interface KartentextDao {

  @Upsert
  suspend fun upsert(kartentext: Kartentext)

  @Delete
  suspend fun delete(kartentext: Kartentext)

  @Query("SELECT * FROM Kartentexte WHERE id = :id")
  suspend fun getByID(id: Int): Kartentext?

  @Query("SELECT * FROM Kartentexte")
  suspend fun getAlle(): List<Kartentext>

  @Query("SELECT * FROM Kartentexte WHERE inaktiv = 0")
  suspend fun getAktive(): List<Kartentext>

  @Query("SELECT * FROM Kartentexte WHERE inaktiv = 0 AND gesehen = 0")
  suspend fun getUngesehene(): List<Kartentext>
}
