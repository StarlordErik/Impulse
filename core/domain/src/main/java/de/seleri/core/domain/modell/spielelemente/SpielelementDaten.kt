package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.konstanten.Standardwerte
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDaten(
	override val lokalisierung: Lokalisierung,

	override val selbstErstellt: Boolean = Standardwerte.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Standardwerte.INAKTIV,
	override val favorisiert: Boolean = Standardwerte.FAVORISIERT,
): Spielelement {

	companion object
}
