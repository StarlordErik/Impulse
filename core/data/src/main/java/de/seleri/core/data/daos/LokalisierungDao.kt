package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.spielelemente.LokalisierungEntity

@Dao
interface LokalisierungDao : DatenbankObjektDao<LokalisierungEntity> {

	@Upsert
	override suspend fun upsert(entity: LokalisierungEntity) : Long

	@Delete
	override suspend fun delete(entity: LokalisierungEntity)

	@Query("SELECT * FROM lokalisierungen WHERE spielId = :spielId")
	suspend fun getForSpiel(spielId: Int): List<LokalisierungEntity>

	@Query("SELECT * FROM Lokalisierungen WHERE kategorieID = :kategorieId")
	suspend fun getForKategorie(kategorieId: Int): List<LokalisierungEntity>

	@Query("SELECT * FROM Lokalisierungen WHERE kartentextID = :kartentextId")
	suspend fun getForKartentext(kartentextId: Int): List<LokalisierungEntity>

}
