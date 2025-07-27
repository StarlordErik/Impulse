package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.SpracheMitBezeichnung
import de.seleri.core.domain.modell.EntityModell
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDaten(
	private val entityModellDaten: EntityModellDaten = EntityModellDaten(id = Konstanten.SPIELELEMENT_ID),

	override val lokalisierung: Lokalisierung,

	override val selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Konstanten.INAKTIV,
	override val favorisiert: Boolean = Konstanten.FAVORISIERT,
): EntityModell by entityModellDaten, Spielelement {

	companion object {

		fun forInitialdaten(ogSprache: Sprache, translationen: Collection<SpracheMitBezeichnung>): SpielelementDaten =
			SpielelementDaten(lokalisierung = Lokalisierung.forInitialdaten(ogSprache, translationen))
	}
}
