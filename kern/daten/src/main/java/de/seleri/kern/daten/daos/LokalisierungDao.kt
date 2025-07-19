package de.seleri.kern.daten.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.kern.Sprache
import de.seleri.kern.daten.entities.singles.LokalisierungEntity

@Dao
interface LokalisierungDao {

  @Upsert
  suspend fun upsert(lokalisierung: LokalisierungEntity)

  @Upsert
  suspend fun upsertAll(lokalisierungen: List<LokalisierungEntity>)

  @Delete
  suspend fun delete(lokalisierung: LokalisierungEntity)

  @Query("SELECT * FROM Lokalisierungen WHERE id = :id")
  suspend fun getById(id: Int): LokalisierungEntity?

  @Query("SELECT * FROM Lokalisierungen WHERE bearbeitet = 1")
  suspend fun getBearbeitete(): List<LokalisierungEntity>

  @Query("SELECT * FROM Lokalisierungen WHERE spielID = :spielId")
  suspend fun getForSpiel(spielId: Int): List<LokalisierungEntity>

  @Query("SELECT * FROM Lokalisierungen WHERE spielID = :spielId AND sprache = :inSprache")
  suspend fun getForSpiel(spielId: Int, inSprache: Sprache): LokalisierungEntity?

  @Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId")
  suspend fun getForKategorie(kategorieId: Int): List<LokalisierungEntity>

  @Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId AND sprache = :inSprache")
  suspend fun getForKategorie(kategorieId: Int, inSprache: Sprache): LokalisierungEntity?

  @Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId")
  suspend fun getForKartentext(kartentextId: Int): List<LokalisierungEntity>

  @Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId AND sprache = :inSprache")
  suspend fun getForKartentext(kartentextId: Int, inSprache: Sprache): LokalisierungEntity?
}
