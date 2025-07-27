package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.SpracheMitBezeichnung

data class Translation(
	private val entityModellDaten: EntityModellDaten = EntityModellDaten(id = Konstanten.TRANSLATION_ID),

	val sprache: Sprache,
	val bezeichnung: String,
	val bearbeitet: Boolean = Konstanten.BEARBEITET,
): EntityModell by entityModellDaten {

	companion object {

		fun forInitialdaten(
			spracheMitBezeichnung: SpracheMitBezeichnung
		): Translation =
			Translation(
				sprache = spracheMitBezeichnung.sprache,
				bezeichnung = spracheMitBezeichnung.bezeichnung,
			)
	}
}
