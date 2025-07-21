package de.seleri.core.domain.model.metadaten

import de.seleri.core.domain.model.DEFAULT_BILD_DATEINAME
import de.seleri.core.domain.model.DEFAULT_ID
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten

class SpielMetaObjekt(
	id: Int = DEFAULT_ID,

	bildDateiname: String? = DEFAULT_BILD_DATEINAME,
): DatenbankObjekt by DatenbankObjektDaten(id), SpielMeta by SpielMetaDaten(bildDateiname)
