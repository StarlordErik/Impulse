package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation

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
		Sprache.DE -> 2
		Sprache.EN -> 3
	}

	if (ogTranslation == null) {
		if (erikTranslation != null || deTranslation != null || enTranslation != null) {
			error("Es fehlt eine Übersetzung für \"$ogSprache\"!")
		}
		return null
	} else {
		val translationen = mutableListOf(ogTranslation)
		if (erikTranslation != null) translationen += erikTranslation

		val neueID = ogTranslation.id.translationID + idBooster

		if (ogSprache == Sprache.DE) {
			if (deTranslation != null) {
				translationen += deTranslation
			} else {
				val neueTranslation = Translation(TranslationIDint(neueID), ogSprache, ogTranslation.bezeichnung)
			}
			if (enTranslation != null) translationen += enTranslation
		}

		if (ogSprache == Sprache.EN) {
			if (deTranslation != null) translationen += deTranslation
			if (enTranslation != null) {
				translationen += enTranslation
			} else {
				val neueTranslation = Translation(TranslationIDint(neueID), ogSprache, ogTranslation.bezeichnung)
			}
		}


		if (deTranslation != null) translationen += deTranslation
		if (enTranslation != null) translationen += enTranslation

		val neueTranslationen: Translation?
		if (ogSprache !in echteTranslationen.map { it.sprache }) {
			neueTranslationen = Translation(TranslationIDint(neueID), ogSprache, ogTranslation.bezeichnung)
		} else neueTranslationen = null
	}
}

fun generiereLokalisierung(
	ogSprache: Sprache, lokalisierungEingabe: LokalisierungEingabe
): Lokalisierung {
	val mehrTranslationen = lokalisierungEingabe.translationen.toMutableList()

	val vorhandeneSprachen = lokalisierungEingabe.translationen.map { it.sprache }
	val ogTranslation = lokalisierungEingabe.translationen.find { it.sprache == ogSprache }

	if (ogSprache !in vorhandeneSprachen && ogTranslation != null) {
		val neueID = ogTranslation.id.translationID + TRANSLATION_ID_BOOSTER
		mehrTranslationen += TranslationEingabe(
			id = TranslationIDint(neueID), sprache = ogSprache, bezeichnung = ogTranslation.bezeichnung
		)
	}

	return Lokalisierung(
		id = lokalisierungEingabe.id,
		ogSprache = ogSprache,
		translationen = mehrTranslationen.map { generiereTranslation(it) })
}
