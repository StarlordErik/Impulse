package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.KartentextEntity

@Dao
interface KartentextDao {

	@Upsert
	suspend fun upsert(kartentext: KartentextEntity): Long

	@Update
	suspend fun update(kartentexte: Collection<KartentextEntity>)

	@Delete
	suspend fun delete(kartentext: KartentextEntity)

	@Query("SELECT * FROM Kartentexte WHERE id = :kartentextId")
	suspend fun get(kartentextId: Int): KartentextEntity
}
