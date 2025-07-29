package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.LokalisierungEntityRoom

@Dao
interface LokalisierungDao : DatenbankObjektDao<LokalisierungEntityRoom> {

	@Upsert
	override suspend fun upsert(entity: LokalisierungEntityRoom) : Long

	@Delete
	override suspend fun delete(entity: LokalisierungEntityRoom)

	@Query("SELECT * FROM lokalisierungen WHERE spielId = :spielId")
	suspend fun getForSpiel(spielId: Int): List<LokalisierungEntityRoom>

	@Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId")
	suspend fun getForKategorie(kategorieId: Int): List<LokalisierungEntityRoom>

	@Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId")
	suspend fun getForKartentext(kartentextId: Int): List<LokalisierungEntityRoom>

}
