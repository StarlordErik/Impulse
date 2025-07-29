package de.seleri.core.domain.entities.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.TranslationID
import kotlinx.serialization.Serializable

@Serializable
data class TranslationEntity(
	override val id: TranslationID,

	val lokalisierungID: LokalisierungID,
// Kombination an lokalisierungID und sprache ist eindeutig
	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean,
): Entity
