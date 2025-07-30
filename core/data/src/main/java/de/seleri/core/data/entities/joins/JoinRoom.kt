package de.seleri.core.data.entities.joins

import de.seleri.core.data.entities.EntityRoom

interface JoinRoom: EntityRoom {

	val firstID: Int
	val secondID: Int
}
