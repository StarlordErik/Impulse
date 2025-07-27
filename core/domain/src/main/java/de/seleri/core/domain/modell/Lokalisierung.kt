package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.mapper.eingabeUtils.SpracheMitBezeichnung

const val TRANSLATION_ID_BOOSTER = 1000000

data class Lokalisierung(
	override val id: LokalisierungIDint,

	val ogSprache: Sprache, val translationen: Collection<Translation>
): EntityModell {

	companion object {

		fun forInitialdaten(
			ogSprache: Sprache, id: LokalisierungIDint, translationen: Collection<SpracheMitBezeichnung>
		): Lokalisierung {
			val mehrTranslationen = translationen.toMutableList()

			val vorhandeneSprachen = translationen.map { it.sprache }
			val ogTranslation = translationen.find { it.sprache == ogSprache }

			if (ogSprache !in vorhandeneSprachen && ogTranslation != null) {
				val neueID = ogTranslation.id.translationID + TRANSLATION_ID_BOOSTER
				mehrTranslationen += SpracheMitBezeichnung(
					id = TranslationIDint(neueID), sprache = ogSprache, bezeichnung = ogTranslation.bezeichnung
				)
			}

			return Lokalisierung(
				id = id, ogSprache = ogSprache, translationen = mehrTranslationen.map { Translation.forInitialdaten(it) })
		}
	}
}
