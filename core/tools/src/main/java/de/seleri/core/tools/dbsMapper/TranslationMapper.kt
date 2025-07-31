package de.seleri.core.tools.dbsMapper

import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Translation

fun Translation.toDatenbankSlice(lokalisierungsID: LokalisierungID): DatenbankSlice =
	DatenbankSlice(translationen = mutableListOf(this.toEntity(lokalisierungsID)))
