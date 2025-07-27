package de.seleri.core.domain.entities.joins

import de.seleri.core.common.idInt.IDint

open class JoinEntity(
	open val firstID: IDint, open val secondID: IDint
)
