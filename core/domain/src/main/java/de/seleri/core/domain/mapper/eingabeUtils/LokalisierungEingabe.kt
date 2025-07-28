package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

const val DE_ID_BOOSTER = 2
const val EN_ID_BOOSTER = 3
const val ANZAHL_TRANSLATION_AUSGABEN_BEI_FEHLER = 3
const val ANZAHL_TRANSLATION_BUCHSTABEN_PRO_FEHLER = 30

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

@Suppress("LongParameterList")
fun Lokalisierung.Companion.fromSkriptForKartentexte(
	freieLokalisierungID: Int,
	ogSprache: Sprache,
	ogTranslationen: List<Translation?>,
	erikTranslationen: List<Translation?>,
	deTranslationen: List<Translation?>,
	enTranslationen: List<Translation?>
): Pair<List<Lokalisierung>, Int> {

	// @formatter:off
	require(
		ogTranslationen.size == erikTranslationen.size
			&& ogTranslationen.size == deTranslationen.size
			&& ogTranslationen.size == enTranslationen.size
	) {
		"Die Kartentext-Translation-Listen sind nicht gleich lang! Betroffen sind die folgenden Kartentexte:\n" +
			"${ogTranslationen.take(ANZAHL_TRANSLATION_AUSGABEN_BEI_FEHLER).map {
				it?.bezeichnung?.take(ANZAHL_TRANSLATION_BUCHSTABEN_PRO_FEHLER)
					?.replace("\n", "\\n")?.replace("\t", "\\t")
			}}..."
	}
	// @formatter:on

	var anzahlNeueLokalisierungen = 0

	val translationenProKartentext = ogTranslationen
		.zip(erikTranslationen)
		.zip(deTranslationen.zip(enTranslationen)) { (og, erik), (de, en) ->
			listOf(og, erik, de, en)
		}

	val lokalisierungen = mutableListOf<Lokalisierung>()

	translationenProKartentext.forEach { translations ->
		if (translations.any { it != null }) {
			val neueID = freieLokalisierungID + anzahlNeueLokalisierungen++

			@Suppress("MagicNumber")
			val lokalisierung = Lokalisierung.fromSkript(
				neueID, ogSprache, translations[0], translations[1], translations[2], translations[3]
			)
			if (lokalisierung != null) {
				lokalisierungen += lokalisierung
			}
		}
	}

	return lokalisierungen.toList() to anzahlNeueLokalisierungen
}
