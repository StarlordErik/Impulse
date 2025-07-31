package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.TranslationID
import de.seleri.core.common.konstanten.Initialwerte

data class Translation(
	override val id: TranslationID,

	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean = Initialwerte.BEARBEITET,
): EntityModell {

	companion object
}
