package de.seleri.core.domain.entities.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint


data class LokalisierungEntity(
	override val id: LokalisierungIDint,

	val ogSprache: Sprache
): Entity
