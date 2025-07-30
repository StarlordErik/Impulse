package de.seleri.core.data.daos

import de.seleri.core.data.entities.EntityRoom

interface Updateable<E: EntityRoom> {

	suspend fun update(entity: E): Int
}
