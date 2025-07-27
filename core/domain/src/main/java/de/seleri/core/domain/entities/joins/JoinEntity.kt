package de.seleri.core.domain.entities.joins

import de.seleri.core.common.idInt.DatenbankobjektIDint

open class JoinEntity(
	open val firstID: DatenbankobjektIDint, open val secondID: DatenbankobjektIDint
)
