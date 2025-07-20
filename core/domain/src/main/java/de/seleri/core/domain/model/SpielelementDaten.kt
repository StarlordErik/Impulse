package de.seleri.core.domain.model

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.BestandteilID

abstract class SpielelementDaten(
	val datenbankObjektDaten: DatenbankObjektDaten,

	val lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
): DatenbankObjekt by datenbankObjektDaten, Spielelement
