package de.seleri.core.data.daos.compositePk.joins

import androidx.room.Delete
import androidx.room.Insert
import de.seleri.core.data.daos.compositePk.CompositePkDAO
import de.seleri.core.data.entities.joins.SpielXKategorieRoom

interface SpielXKategorieDAO: CompositePkDAO<SpielXKategorieRoom> {

	@Delete
	override suspend fun delete(entity: SpielXKategorieRoom): Int

	@Insert
	override suspend fun insert(entity: SpielXKategorieRoom)

	@Insert
	override suspend fun insertAll(entities: Collection<SpielXKategorieRoom>)
}
