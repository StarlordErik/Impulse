package de.seleri.core.domain.model.metadaten

import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten

class SpielMeta(
	override val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),
	val bildDateiname: String? = null,
) : DatenbankObjekt by datenbankObjektDaten
