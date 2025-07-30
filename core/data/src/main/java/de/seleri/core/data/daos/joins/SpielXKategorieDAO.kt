package de.seleri.core.data.daos.joins

import androidx.room.Delete
import androidx.room.Insert
import de.seleri.core.data.entities.joins.SpielXKategorieRoom

interface SpielXKategorieDAO: JoinDAO<SpielXKategorieRoom> {

	@Insert
	override suspend fun insert(entity: SpielXKategorieRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<SpielXKategorieRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: SpielXKategorieRoom): Int
}
