package de.seleri.core.domain.repositories.base

import de.seleri.core.common.ids.EntityID
import de.seleri.core.domain.modell.IDable

interface GetByIDRepo<E: IDable<ID>, ID: EntityID>: EntityRepo<E> {

	suspend fun get(id: ID): E
}
