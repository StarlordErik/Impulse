package de.seleri.core.data.daos.singles.lokStern.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.SpielRoom

@Dao
interface SpielDao: SpielelementDao<SpielRoom, SpielID> {

	@Insert
	override suspend fun insert(entity: SpielRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<SpielRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: SpielRoom): Int

	@Update
	override suspend fun update(entity: SpielRoom): Int

	@Query("SELECT * FROM spiele WHERE id = :spielelementID")
	override suspend fun get(spielelementID: SpielID): SpielRoom
}
