package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Translation

const val ANZAHL_AN_SPRACHEN = 4
fun Translation.Companion.fromSkript(
	freieTranslationID: Int, sprache: Sprache, bezeichnung: String
): Pair<Translation?, Int> {
	return if (bezeichnung.isNotBlank()) Translation(TranslationIDint(freieTranslationID), sprache, bezeichnung) to 0
	else null to -1
}

fun Translation.Companion.fromSkriptForKartentexte(
	freieTranslationID: Int, sprache: Sprache, bezeichnungen: List<String>
): Pair<List<Translation>?, Int> {
	var anzahlNeuerTranslationen = 0

	val translationen = mutableListOf<Translation>()

	bezeichnungen.forEach { bezeichnung ->
		val neueID = freieTranslationID + anzahlNeuerTranslationen
		val translationInfo = Translation.fromSkript(neueID, sprache, bezeichnung)

		val translation = translationInfo.first
		if (translation != null) translationen += translation

		anzahlNeuerTranslationen += (1 + translationInfo.second) * ANZAHL_AN_SPRACHEN
	}

	val maxID = anzahlNeuerTranslationen - 1

	return if (translationen.isEmpty()) {
		null to maxID
	} else {
		translationen.toList() to maxID
	}
}
