package de.seleri.core.domain.entities.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.id.LokalisierungID
import kotlinx.serialization.Serializable

@Serializable
data class LokalisierungEntity(
	override val id: LokalisierungID,

	val ogSprache: Sprache
): Entity
