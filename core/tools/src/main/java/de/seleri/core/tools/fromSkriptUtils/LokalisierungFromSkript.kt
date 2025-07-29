package de.seleri.core.tools.fromSkriptUtils

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
): Pair<Lokalisierung?, Int> {
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
		return null to -1
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
		) to 1
	}
}

@Suppress("LongParameterList")
fun Lokalisierung.Companion.fromSkriptForKartentexte(
	freieLokalisierungID: Int,
	ogSprache: Sprache,
	ogTranslationen: List<Translation>?,
	erikTranslationen: List<Translation>?,
	deTranslationen: List<Translation>?,
	enTranslationen: List<Translation>?
): Pair<List<Lokalisierung>?, Int> {
	if (ogTranslationen == null) {
		if (erikTranslationen != null || deTranslationen != null || enTranslationen != null) {
			error("Es fehlt eine Übersetzung für \"$ogSprache\"!")
		}
		return null to -1
	} else {

		val echteTranslationen = listOfNotNull(
			ogTranslationen, erikTranslationen, deTranslationen, enTranslationen
		)

		val translationenProKartentext = echteTranslationen[0].indices.map { index ->
			echteTranslationen.map { it[index] }
		}

		// @formatter:off
		if (ogTranslationen.size != translationenProKartentext.size) {
			error("Die Kartentext-Translation-Listen sind nicht gleich lang! U.a. betroffene Kartentexte:\n" +
				"${ogTranslationen.take(ANZAHL_TRANSLATION_AUSGABEN_BEI_FEHLER).map {
					it.bezeichnung.take(ANZAHL_TRANSLATION_BUCHSTABEN_PRO_FEHLER)
					.replace("\n", "\\n").replace("\t", "\\t")
				}}...")
		}
		// @formatter:on

		var anzahlNeueLokalisierungen = 0
		val lokalisierungen = mutableListOf<Lokalisierung>()

		translationenProKartentext.forEach { kartentextTranslationen ->
			val neueID = freieLokalisierungID + anzahlNeueLokalisierungen

			val ogTranslation = kartentextTranslationen.first()
			val erikTranslation = kartentextTranslationen.find { it.sprache == Sprache.ERIK }
			val deTranslation = kartentextTranslationen.find { it.sprache == Sprache.DE }
			val enTranslation = kartentextTranslationen.find { it.sprache == Sprache.EN }

			val lokalisierungInfo = Lokalisierung.fromSkript(
				neueID, ogSprache, ogTranslation, erikTranslation, deTranslation, enTranslation
			)
			val lokalisierung = lokalisierungInfo.first
			if (lokalisierung != null) {
				lokalisierungen += lokalisierung
			}
			anzahlNeueLokalisierungen += lokalisierungInfo.second
		}

		val maxID = anzahlNeueLokalisierungen - 1

		return if (lokalisierungen.isEmpty()) {
			null to maxID
		} else {
			lokalisierungen.toList() to maxID
		}
	}
}
