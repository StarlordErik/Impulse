package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import de.seleri.kern.daten.entities.Kategorie
import de.seleri.kern.daten.relationen.KategorieMitKartentexten

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

  @Transaction
  @Query("SELECT * FROM Kategorien WHERE id = :kategorieId")
  fun getMitKartentexten(kategorieId: Int): KategorieMitKartentexten

  /*
  @Transaction
  @Query(
    """
  SELECT k.* FROM Kategorien k
  INNER JOIN KategorieXKartentext x ON k.id = x.kategorieID
  INNER JOIN Kartentexte t ON x.kartentextID = t.id
  WHERE k.id = :kategorieId AND k.inaktiv = 0 AND t.inaktiv = 0
"""
  )
  fun getMitAktivenKartentexten(kategorieId: Int): KategorieMitKartentexten

  @Transaction
  @Query(
    """
  SELECT k.* FROM Kategorien k
  INNER JOIN KategorieXKartentext x ON k.id = x.kategorieID
  INNER JOIN Kartentexte t ON x.kartentextID = t.id
  WHERE k.id = :kategorieId AND k.inaktiv = 0 AND t.inaktiv = 0 AND t.gesehen = 0
"""
  )
  fun getMitUngesehenenKartentexten(kategorieId: Int): KategorieMitKartentexten
  */
}
