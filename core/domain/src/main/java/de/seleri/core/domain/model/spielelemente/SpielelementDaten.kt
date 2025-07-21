package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID

data class SpielelementDaten(
	override val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	val lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	val ogSprache: Sprache = Sprache.DE,
	val selbstErstellt: Boolean = false,
	val inaktiv: Boolean = false,
	val favorisiert: Boolean = false,
): DatenbankObjekt by datenbankObjektDaten, Spielelement
