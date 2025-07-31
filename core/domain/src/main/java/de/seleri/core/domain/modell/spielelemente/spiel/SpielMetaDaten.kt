package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.konstanten.Standardwerte
import de.seleri.core.domain.modell.spielelemente.Spielelement
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

class SpielMetaDaten(
	private val spielelementDaten: SpielelementDaten,

	override val bildDateiname: String? = Standardwerte.BILD_DATEINAME,
): Spielelement by spielelementDaten, SpielMeta {

	companion object
}
