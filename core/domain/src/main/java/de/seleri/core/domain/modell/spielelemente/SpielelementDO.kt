package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.konstanten.Default
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDO(
	override val lokalisierung: Lokalisierung,

	override val selbstErstellt: Boolean = Default.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Default.INAKTIV,
	override val favorisiert: Boolean = Default.FAVORISIERT,

	): SpielelementDaten {

	companion object
}
