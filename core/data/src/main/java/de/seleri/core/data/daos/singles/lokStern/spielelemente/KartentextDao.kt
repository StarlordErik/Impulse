package de.seleri.core.data.daos.singles.lokStern.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KartentextRoom

@Dao
interface KartentextDao: SpielelementDao<KartentextRoom, KartentextID> {

	@Insert
	override suspend fun insert(entity: KartentextRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<KartentextRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: KartentextRoom): Int

	@Update
	override suspend fun update(entity: KartentextRoom): Int

	@Query("SELECT * FROM kartentexte WHERE id = :spielelementID")
	override suspend fun get(spielelementID: KartentextID): KartentextRoom
}
