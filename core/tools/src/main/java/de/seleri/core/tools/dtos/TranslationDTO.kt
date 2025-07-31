package de.seleri.core.tools.dtos

import de.seleri.core.common.Sprache
import de.seleri.core.common.entities.TranslationEntity
import de.seleri.core.common.ids.LokalisierungID
import kotlinx.serialization.Serializable

@Serializable
data class TranslationDTO(

	override val lokalisierungID: LokalisierungID,

	override val sprache: Sprache,

	override val bezeichnung: String,

	override val bearbeitet: Boolean,
): LokSternDTO, TranslationEntity
