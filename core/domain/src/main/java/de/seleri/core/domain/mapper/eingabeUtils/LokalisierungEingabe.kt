package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Lokalisierung

data class LokalisierungEingabe(
	val id: LokalisierungIDint, val translationen: Collection<TranslationEingabe>
)

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
