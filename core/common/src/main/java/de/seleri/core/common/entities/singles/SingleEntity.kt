package de.seleri.core.common.entities.singles

import de.seleri.core.common.entities.Entity
import de.seleri.core.common.id.EntityID

interface SingleEntity: Entity {

	val id: EntityID
}
