package de.seleri.core.common.entities.singles

import de.seleri.core.common.entities.Entity
import de.seleri.core.common.ids.EntityID

interface SingleEntity<EID: EntityID>: Entity {

	val id: EID
}
