package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import de.seleri.core.data.entities.singles.KartentextEntity

@Dao
interface KartentextDao {
	@Upsert
	suspend fun upsert(kartentext: KartentextEntity)

	@Delete
	suspend fun delete(kartentext: KartentextEntity)

}
