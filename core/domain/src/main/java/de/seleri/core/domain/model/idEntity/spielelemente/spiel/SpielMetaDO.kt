package de.seleri.core.domain.model.idEntity.spielelemente.spiel

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.common.konstanten.Default
import de.seleri.core.domain.model.idEntity.spielelemente.Spielelement
import de.seleri.core.domain.model.idEntity.spielelemente.SpielelementDO
import de.seleri.core.domain.model.idEntity.spielelemente.SpielelementDaten

class SpielMetaDO(
	private val spielelementDaten: SpielelementDO,

	override val bildDateiname: String? = Default.BILD_DATEINAME,
): SpielelementDaten by spielelementDaten, Spielelement<SpielID>, SpielMeta {

	companion object
}
