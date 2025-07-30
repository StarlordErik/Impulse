package de.seleri.core.data.daos

import de.seleri.core.data.entities.singles.IDEntityRoom

interface EntityDao<E: IDEntityRoom> {

	suspend fun insert(entity: E): Long
	suspend fun insertAll(entities: Collection<E>): List<Long>
	suspend fun update(entity: E): Int
	suspend fun delete(entity: E): Int
}
