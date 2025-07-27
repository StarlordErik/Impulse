package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.mapper.eingabeUtils.TranslationEingabe

data class Translation(
	override val id: TranslationIDint,

	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean = Konstanten.BEARBEITET,
): EntityModell {

	companion object {

		fun forInitialdaten(
			translationEingabe: TranslationEingabe
		): Translation =
			Translation(
				id = translationEingabe.id,
				sprache = translationEingabe.sprache,
				bezeichnung = translationEingabe.bezeichnung,
			)
	}
}
