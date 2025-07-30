package de.seleri.core.data.daos.joins

import androidx.room.Delete
import androidx.room.Insert
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom

interface KategorieXKartentextDAO: JoinDAO<KategorieXKartentextRoom> {

	@Insert
	override suspend fun insert(entity: KategorieXKartentextRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<KategorieXKartentextRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: KategorieXKartentextRoom): Int
}
