package de.seleri.core.data.daos.singles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.seleri.core.common.id.EntityID
import de.seleri.core.data.daos.EntityDAO
import de.seleri.core.data.entities.singles.LokalisierungRoom

@Dao
interface LokalisierungDAO: EntityDAO<LokalisierungRoom> {

	@Insert
	override suspend fun insert(entity: LokalisierungRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<LokalisierungRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: LokalisierungRoom): Int

	@Query("SELECT * FROM lokalisierungen WHERE id = :id")
	suspend fun get(id: EntityID): LokalisierungRoom
}
