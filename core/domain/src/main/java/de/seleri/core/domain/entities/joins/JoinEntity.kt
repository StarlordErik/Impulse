package de.seleri.core.domain.entities.joins

import de.seleri.core.common.idInt.EntityIDint

interface JoinEntity {

	val firstID: EntityIDint
	val secondID: EntityIDint
}
