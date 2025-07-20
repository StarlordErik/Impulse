package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID

data class SpielelementDaten(
	val datenbankObjektDaten: DatenbankObjektDaten,

	val lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
): DatenbankObjekt by datenbankObjektDaten, Spielelement
