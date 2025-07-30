package de.seleri.core.data.daos.singles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import de.seleri.core.data.daos.EntityDao
import de.seleri.core.data.entities.singles.LokalisierungRoom

@Dao
interface LokalisierungDao: EntityDao<LokalisierungRoom> {

	@Insert
	override suspend fun insert(entity: LokalisierungRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<LokalisierungRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: LokalisierungRoom): Int
}
