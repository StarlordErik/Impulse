package de.seleri.core.data.daos

import de.seleri.core.data.entities.RoomEntity

interface EntityDAO<E: RoomEntity> {

	suspend fun insert(entity: E): Long
	suspend fun insertAll(entities: Collection<E>): List<Long>
	suspend fun delete(entity: E): Int
}
