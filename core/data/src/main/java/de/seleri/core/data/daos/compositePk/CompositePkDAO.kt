package de.seleri.core.data.daos.compositePk

import de.seleri.core.data.daos.EntityDAO
import de.seleri.core.data.entities.RoomEntity

interface CompositePkDAO<E: RoomEntity>: EntityDAO<E> {

	suspend fun insert(entity: E)
	suspend fun insertAll(entities: Collection<E>)
}
