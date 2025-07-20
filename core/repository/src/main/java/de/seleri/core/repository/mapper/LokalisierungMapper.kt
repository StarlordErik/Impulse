package de.seleri.core.repository.mapper

import de.seleri.core.common.LokalisierungVon
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung

fun LokalisierungEntity.toDomain(): Lokalisierung {
	val spielelementFK = when {
		spielID != null -> LokalisierungVon.SPIEL to spielID!!
		kategorieID != null -> LokalisierungVon.KATEGORIE to kategorieID!!
		kartentextID != null -> LokalisierungVon.KARTENTEXT to kartentextID!!
		else -> error("LokalisierungEntity besitzt keinen Fremdschlüssel - weder für Spiel, Kategorie noch Kartentext!")
	}

	return Lokalisierung(
		datenbankObjektDaten = DatenbankObjektDaten(id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet, spielelementFK = spielelementFK
	)
}

fun Lokalisierung.toEntity(): LokalisierungEntity {
	val lokalisierungFK = spielelementFK.first
	val spielelementID = spielelementFK.second

	return LokalisierungEntity(
		id = this.id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = if (lokalisierungFK == LokalisierungVon.SPIEL) spielelementID else null,
		kategorieID = if (lokalisierungFK == LokalisierungVon.KATEGORIE) spielelementID else null,
		kartentextID = if (lokalisierungFK == LokalisierungVon.KARTENTEXT) spielelementID else null
	)
}
