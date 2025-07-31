package de.seleri.core.tools.dtos.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.entities.singles.LokalisierungEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LokalisierungDTO(

	@SerialName(value = "id")
	override val lokalisierungID: Int,

	override val ogSprache: Sprache
): SingleDTO, LokalisierungEntity
