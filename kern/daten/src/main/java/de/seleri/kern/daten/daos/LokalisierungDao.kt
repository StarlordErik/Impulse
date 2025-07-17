package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.kern.daten.entities.Lokalisierung
import de.seleri.kern.daten.utils.Sprache

@Dao
interface LokalisierungDao {

  @Upsert
  suspend fun upsert(lokalisierung: Lokalisierung)

  @Upsert
  suspend fun upsertAll(lokalisierungen: List<Lokalisierung>)

  @Delete
  suspend fun delete(lokalisierung: Lokalisierung)

  @Query("SELECT * FROM Lokalisierungen WHERE id = :id")
  suspend fun getById(id: Int): Lokalisierung?

  @Query("SELECT * FROM Lokalisierungen WHERE bearbeitet = 1")
  suspend fun getBearbeitete(): List<Lokalisierung>

  @Query("SELECT * FROM Lokalisierungen WHERE spielID = :spielId")
  suspend fun getForSpiel(spielId: Int): List<Lokalisierung>

  @Query("SELECT * FROM Lokalisierungen WHERE spielID = :spielId AND sprache = :inSprache")
  suspend fun getForSpiel(spielId: Int, inSprache: Sprache): Lokalisierung?

  @Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId")
  suspend fun getForKategorie(kategorieId: Int): List<Lokalisierung>

  @Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId AND sprache = :inSprache")
  suspend fun getForKategorie(kategorieId: Int, inSprache: Sprache): Lokalisierung?

  @Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId")
  suspend fun getForKartentext(kartentextId: Int): List<Lokalisierung>

  @Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId AND sprache = :inSprache")
  suspend fun getForKartentext(kartentextId: Int, inSprache: Sprache): Lokalisierung?
}
