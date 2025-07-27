package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.SpracheMitBezeichnung


data class Lokalisierung(
	private val entityModellDaten: EntityModellDaten = EntityModellDaten(id = Konstanten.LOKALISIERUNG_ID),

	val ogSprache: Sprache, val translationen: Collection<Translation>
): EntityModell by entityModellDaten {

	companion object {

		fun forInitialdaten(
			ogSprache: Sprache, translationen: Collection<SpracheMitBezeichnung>
		): Lokalisierung {
			val mehrTranslationen = translationen.toMutableList()

			val vorhandeneSprachen = translationen.map { it.sprache }
			val ogTranslation = translationen.find { it.sprache == ogSprache }

			if (ogSprache !in vorhandeneSprachen && ogTranslation != null) {
				mehrTranslationen += SpracheMitBezeichnung(
					sprache = ogSprache, bezeichnung = ogTranslation.bezeichnung
				)
			}

			return Lokalisierung(
				ogSprache = ogSprache, translationen = mehrTranslationen.map { Translation.forInitialdaten(it) })
		}
	}
}
