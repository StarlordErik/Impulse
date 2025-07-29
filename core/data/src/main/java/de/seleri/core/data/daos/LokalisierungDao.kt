package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.LokalisierungRoom

@Dao
interface LokalisierungDao: DatenbankObjektDao<LokalisierungRoom> {

	@Upsert
	override suspend fun upsert(entity: LokalisierungRoom): Long

	@Delete
	override suspend fun delete(entity: LokalisierungRoom)

	@Query("SELECT * FROM lokalisierungen WHERE spielId = :spielId")
	suspend fun getForSpiel(spielId: Int): List<LokalisierungRoom>

	@Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId")
	suspend fun getForKategorie(kategorieId: Int): List<LokalisierungRoom>

	@Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId")
	suspend fun getForKartentext(kartentextId: Int): List<LokalisierungRoom>

}
