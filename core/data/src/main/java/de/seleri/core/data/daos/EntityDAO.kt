package de.seleri.core.data.daos

import de.seleri.core.data.entities.RoomEntity

interface EntityDAO<E: RoomEntity> {

	suspend fun delete(entity: E): Int
}
