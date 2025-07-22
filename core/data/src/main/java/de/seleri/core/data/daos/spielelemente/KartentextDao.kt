package de.seleri.core.data.daos.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity

@Dao
interface KartentextDao : SpielelementDao<KartentextEntity> {

	@Upsert
	override suspend fun upsert(entity: KartentextEntity): Long

	@Update
	suspend fun update(kartentexte: Collection<KartentextEntity>)

	@Delete
	override suspend fun delete(entity: KartentextEntity)

	@Query("SELECT * FROM Kartentexte WHERE id = :spielelementId")
	override suspend fun get(spielelementId: Int): KartentextEntity
}
