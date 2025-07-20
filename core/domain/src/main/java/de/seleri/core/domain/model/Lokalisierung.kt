package de.seleri.core.domain.model

import de.seleri.core.common.LokalisierungFK
import de.seleri.core.common.Sprache

data class Lokalisierung(
	val datenbankObjektDaten: DatenbankObjektDaten,

	val bezeichnung: String,
	val sprache: Sprache,
	val bearbeitet: Boolean,

	val spielelementFK: Pair<LokalisierungFK, Int>,
): DatenbankObjekt by datenbankObjektDaten, Bestandteil
