package de.seleri.core.data.daos.singles.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom

@Dao
interface KartentextDAO: SpielelementDAO<KartentextRoom, KartentextID> {

	@Delete
	override suspend fun delete(entity: KartentextRoom): Int

	@Insert
	override suspend fun insert(entity: KartentextRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<KartentextRoom>): List<Long>

	@Query("SELECT * FROM kartentexte WHERE id = :spielelementID")
	override suspend fun get(spielelementID: KartentextID): KartentextRoom

	@Update
	override suspend fun update(entity: KartentextRoom): Int
}
