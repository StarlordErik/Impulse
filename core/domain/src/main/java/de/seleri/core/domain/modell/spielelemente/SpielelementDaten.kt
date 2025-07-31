package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.konstanten.Initialwerte
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDaten(
	override val lokalisierung: Lokalisierung,

	override val selbstErstellt: Boolean = Initialwerte.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Initialwerte.INAKTIV,
	override val favorisiert: Boolean = Initialwerte.FAVORISIERT,
): Spielelement {

	companion object
}
