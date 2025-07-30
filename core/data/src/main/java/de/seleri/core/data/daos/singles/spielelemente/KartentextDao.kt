package de.seleri.core.data.daos.singles.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom

@Dao
interface KartentextDao: SpielelementDao<KartentextRoom> {

	@Upsert
	override suspend fun upsert(entity: KartentextRoom): Long

	@Update
	suspend fun update(kartentexte: Collection<KartentextRoom>)

	@Delete
	override suspend fun delete(entity: KartentextRoom)

	@Query("SELECT * FROM Kartentexte WHERE id = :spielelementID")
	override suspend fun get(spielelementID: Int): KartentextRoom
}
