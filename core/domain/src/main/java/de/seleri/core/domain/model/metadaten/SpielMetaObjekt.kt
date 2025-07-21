package de.seleri.core.domain.model.metadaten

import de.seleri.core.domain.model.DEFAULT_ID
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten

class SpielMetaObjekt(
	id: Int = DEFAULT_ID,

	private val spielMetaDaten: SpielMetaDaten = SpielMetaDaten(),
): DatenbankObjekt by DatenbankObjektDaten(id), SpielMeta by spielMetaDaten
