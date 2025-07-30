package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.EntityID

interface Join {

	val firstID: EntityID
	val secondID: EntityID
}
