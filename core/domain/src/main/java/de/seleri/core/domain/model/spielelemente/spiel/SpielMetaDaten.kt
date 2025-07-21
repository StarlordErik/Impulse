package de.seleri.core.domain.model.spielelemente.spiel

import de.seleri.core.domain.model.spielelemente.Spielelement
import de.seleri.core.domain.model.spielelemente.SpielelementDaten

class SpielMetaDaten(
	private val spielelementDaten: SpielelementDaten,

	override val bildDateiname: String? = null,
): Spielelement by spielelementDaten, SpielMeta
