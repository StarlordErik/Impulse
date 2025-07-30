package de.seleri.core.data.entities.singles

import de.seleri.core.common.id.EntityID
import de.seleri.core.data.entities.EntityRoom

interface SingleRoom: EntityRoom {

	val id: EntityID
}
