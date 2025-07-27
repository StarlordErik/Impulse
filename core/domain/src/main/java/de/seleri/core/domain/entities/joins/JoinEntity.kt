package de.seleri.core.domain.entities.joins

import de.seleri.core.common.idInt.EntityIDint

open class JoinEntity(
	open val firstID: EntityIDint, open val secondID: EntityIDint
)
