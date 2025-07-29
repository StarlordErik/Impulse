package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.id.TranslationID

data class Translation(
	override val id: TranslationID,

	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean = Konstanten.BEARBEITET,
): EntityModell {

	companion object
}
