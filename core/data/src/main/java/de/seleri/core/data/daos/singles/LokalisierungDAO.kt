package de.seleri.core.data.daos.singles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.seleri.core.data.entities.singles.LokalisierungRoom

@Dao
interface LokalisierungDAO: SingleDAO<LokalisierungRoom> {

	@Delete
	override suspend fun delete(entity: LokalisierungRoom): Int

	@Insert
	override suspend fun insert(entity: LokalisierungRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<LokalisierungRoom>): List<Long>

	@Query("SELECT * FROM lokalisierungen WHERE id = :id")
	suspend fun get(id: Int): LokalisierungRoom
}
