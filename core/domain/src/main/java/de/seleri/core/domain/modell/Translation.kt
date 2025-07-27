package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint

data class Translation(
	override val id: TranslationIDint,

	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean = Konstanten.BEARBEITET,
): EntityModell
