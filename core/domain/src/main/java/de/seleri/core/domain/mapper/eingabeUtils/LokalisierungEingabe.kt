package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

const val DE_ID_BOOSTER = 2
const val EN_ID_BOOSTER = 3

@Suppress("LongParameterList")
fun Lokalisierung.Companion.fromSkript(
	freieLokalisierungID: Int,
	ogSprache: Sprache,
	ogTranslation: Translation?,
	erikTranslation: Translation?,
	deTranslation: Translation?,
	enTranslation: Translation?
): Lokalisierung? {
	val idBooster = when (ogSprache) {
		Sprache.OG -> error("Du kannst nicht \"Sprache.OG\" als ogSprache setzen!")
		Sprache.ERIK -> error("Du kannst nicht \"Sprache.ERIK\" als ogSprache setzen!")
		Sprache.DE -> DE_ID_BOOSTER
		Sprache.EN -> EN_ID_BOOSTER
	}

	if (ogTranslation == null) {
		if (erikTranslation != null || deTranslation != null || enTranslation != null) {
			error("Es fehlt eine Übersetzung für \"$ogSprache\"!")
		}
		return null
	} else {
		val translationen = mutableListOf(ogTranslation)
		if (erikTranslation != null) translationen += erikTranslation
		if (deTranslation != null) translationen += deTranslation

		val neueID = ogTranslation.id.translationID + idBooster

		if (ogSprache == Sprache.DE && deTranslation == null) {
			val neueTranslation = Translation(TranslationIDint(neueID), ogSprache, ogTranslation.bezeichnung)
			translationen += neueTranslation
		}

		if (enTranslation != null) translationen += enTranslation

		if (ogSprache == Sprache.EN && enTranslation == null) {
			val neueTranslation = Translation(TranslationIDint(neueID), ogSprache, ogTranslation.bezeichnung)
			translationen += neueTranslation
		}

		return Lokalisierung(
			id = LokalisierungIDint(freieLokalisierungID), ogSprache = ogSprache, translationen = translationen
		)
	}
}
