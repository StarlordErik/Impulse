package de.seleri.core.domain.model

import de.seleri.core.common.DatenbankObjektID
import de.seleri.core.common.Sprache

abstract class SpielelementDaten(
	val datenbankObjektDaten: DatenbankObjektDaten,

	val lokalisierungen: Collection<DatenbankObjektID.LokalisierungID>,

	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
): DatenbankObjekt by datenbankObjektDaten, Spielelement
