package de.seleri.core.common.entities.singles.lokStern

import de.seleri.core.common.Sprache
import de.seleri.core.common.id.LokalisierungID

interface TranslationEntity: LokSternEntity {

	override val id: LokalisierungID get() = lokalisierungID

	val sprache: Sprache
	val bezeichnung: String
	val bearbeitet: Boolean
}
