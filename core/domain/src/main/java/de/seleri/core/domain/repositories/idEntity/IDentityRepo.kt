package de.seleri.core.domain.repositories.idEntity

import de.seleri.core.common.ids.EntityID
import de.seleri.core.domain.modell.idEntity.IDentity
import de.seleri.core.domain.repositories.EntityRepo

interface IDentityRepo<E: IDentity<ID>, ID: EntityID>: EntityRepo<E> {

	suspend fun new(entity: E): ID

	suspend fun get(id: ID): E
}
