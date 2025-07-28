package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.spielelemente.Spielelement
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

class SpielMetaDaten(
	private val spielelementDaten: SpielelementDaten,

	override val bildDateiname: String? = Konstanten.BILD_DATEINAME,
): Spielelement by spielelementDaten, SpielMeta {

	companion object
}
