package de.seleri.core.domain.model

import de.seleri.core.common.LokalisierungVon
import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.ids.SpielelementID

class Lokalisierung(
	id: Int = DEFAULT_ID,

	val bezeichnung: String,
	val sprache: Sprache = Sprache.OG,
	val bearbeitet: Boolean = false,

	val spielelementFK: Pair<LokalisierungVon, SpielelementID>,
): DatenbankObjekt by DatenbankObjektDaten(id), Bestandteil
