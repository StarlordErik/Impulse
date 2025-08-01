package de.seleri.core.data.daos.compositePk.joins

import androidx.room.Delete
import androidx.room.Insert
import de.seleri.core.data.daos.compositePk.CompositePkDAO
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom

interface KategorieXKartentextDAO: CompositePkDAO<KategorieXKartentextRoom> {

	@Delete
	override suspend fun delete(entity: KategorieXKartentextRoom): Int

	@Insert
	override suspend fun insert(entity: KategorieXKartentextRoom)

	@Insert
	override suspend fun insertAll(entities: Collection<KategorieXKartentextRoom>)
}
