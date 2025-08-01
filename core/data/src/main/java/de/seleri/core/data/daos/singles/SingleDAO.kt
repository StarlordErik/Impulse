package de.seleri.core.data.daos.singles

import de.seleri.core.data.daos.EntityDAO
import de.seleri.core.data.entities.singles.SingleRoom

interface SingleDAO<S: SingleRoom>: EntityDAO<S> {

	suspend fun insert(entity: S): Long
	suspend fun insertAll(entities: Collection<S>): List<Long>
}
