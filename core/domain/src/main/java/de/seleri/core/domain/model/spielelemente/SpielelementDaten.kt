package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID

data class SpielelementDaten(
	private val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	override val lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	override val ogSprache: Sprache = Sprache.DE,
	override val selbstErstellt: Boolean = false,
	override val inaktiv: Boolean = false,
	override val favorisiert: Boolean = false,
): DatenbankObjekt by datenbankObjektDaten, Spielelement
