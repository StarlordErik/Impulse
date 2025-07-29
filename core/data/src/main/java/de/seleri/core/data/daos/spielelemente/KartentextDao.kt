package de.seleri.core.data.daos.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntityRoom

@Dao
interface KartentextDao : SpielelementDao<KartentextEntityRoom> {

	@Upsert
	override suspend fun upsert(entity: KartentextEntityRoom): Long

	@Update
	suspend fun update(kartentexte: Collection<KartentextEntityRoom>)

	@Delete
	override suspend fun delete(entity: KartentextEntityRoom)

	@Query("SELECT * FROM Kartentexte WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): KartentextEntityRoom
}
