package de.seleri.core.domain.entities.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.TranslationIDint
import kotlinx.serialization.Serializable

@Serializable
data class TranslationEntity(
	override val id: TranslationIDint,

	val lokalisierungID: LokalisierungIDint,
// Kombination an lokalisierungID und sprache ist eindeutig
	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean,
): Entity
