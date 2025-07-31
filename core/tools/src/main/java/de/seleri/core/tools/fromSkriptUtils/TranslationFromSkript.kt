package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.TranslationID
import de.seleri.core.domain.modell.Translation

fun Translation.Companion.fromSkript(
	freieTranslationID: Int, sprache: Sprache, bezeichnung: String
): Pair<Translation?, Int> {
	return if (bezeichnung.isNotBlank()) Translation(TranslationID(freieTranslationID), sprache, bezeichnung) to 0
	else null to -1
}

fun Translation.Companion.fromSkriptForKartentexte(
	freieTranslationID: Int, sprache: Sprache, bezeichnungen: List<String>
): Pair<List<Translation?>?, Int> {
	var anzahlNeuerTranslationen = 0

	val translationen = mutableListOf<Translation?>()

	bezeichnungen.forEach { bezeichnung ->
		val neueID = freieTranslationID + anzahlNeuerTranslationen
		val translationInfo = Translation.fromSkript(neueID, sprache, bezeichnung)

		val translation = translationInfo.first
		translationen += translation

		anzahlNeuerTranslationen += (1 + translationInfo.second) * Sprache.entries.size
	}

	val maxID = anzahlNeuerTranslationen - 1

	return if (translationen
			.filterNotNull()
			.isEmpty()
	) {
		null to maxID
	} else {
		translationen.toList() to maxID
	}
}
