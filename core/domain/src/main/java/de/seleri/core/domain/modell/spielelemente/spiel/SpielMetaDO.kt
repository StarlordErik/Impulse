package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.common.konstanten.Default
import de.seleri.core.domain.modell.spielelemente.Spielelement
import de.seleri.core.domain.modell.spielelemente.SpielelementDO
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

class SpielMetaDO(
	private val spielelementDaten: SpielelementDO,

	override val bildDateiname: String? = Default.BILD_DATEINAME,
): SpielelementDaten by spielelementDaten, Spielelement<SpielID>, SpielMeta {

	companion object
}
