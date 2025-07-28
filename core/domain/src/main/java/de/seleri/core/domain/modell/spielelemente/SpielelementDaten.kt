package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDaten(
	override val lokalisierung: Lokalisierung,

	override val selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Konstanten.INAKTIV,
	override val favorisiert: Boolean = Konstanten.FAVORISIERT,
): Spielelement {

	companion object
}
