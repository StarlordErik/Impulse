package de.seleri.core.domain.model.metadaten

import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten

class SpielMetaObjekt(
	private val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	private val spielMetaDaten: SpielMetaDaten = SpielMetaDaten(),
): DatenbankObjekt by datenbankObjektDaten, SpielMeta by spielMetaDaten
