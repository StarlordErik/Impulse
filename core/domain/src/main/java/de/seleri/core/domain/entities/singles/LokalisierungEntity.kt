package de.seleri.core.domain.entities.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import kotlinx.serialization.Serializable

@Serializable
data class LokalisierungEntity(
	override val id: LokalisierungIDint,

	val ogSprache: Sprache
): Entity
