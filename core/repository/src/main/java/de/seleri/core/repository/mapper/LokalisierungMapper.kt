package de.seleri.core.repository.mapper

import de.seleri.core.common.LokalisierungVon
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.ids.SpielelementID

fun LokalisierungEntity.toDomain(): Lokalisierung {
	val spielelementFK = when (lokalisierungVon) {
		LokalisierungVon.SPIEL -> LokalisierungVon.SPIEL to SpielelementID.SpielID(spielID!!)
		LokalisierungVon.KATEGORIE -> LokalisierungVon.KATEGORIE to SpielelementID.KategorieID(kategorieID!!)
		LokalisierungVon.KARTENTEXT -> LokalisierungVon.KARTENTEXT to SpielelementID.KartentextID(kartentextID!!)
	}

	return Lokalisierung(
		datenbankObjektDaten = DatenbankObjektDaten(id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielelementFK = spielelementFK
	)
}

fun Lokalisierung.toEntity(): LokalisierungEntity {
	val lokalisierungFK = spielelementFK.first
	val spielelementID = spielelementFK.second.toInt()

	val lol = LokalisierungEntity(
		id = datenbankObjektDaten.id,
		bezeichnung = bezeichnung, sprache = sprache, bearbeitet = bearbeitet, lokalisierungVon = lokalisierungFK,
		spielID = if (lokalisierungFK == LokalisierungVon.SPIEL) spielelementID else null,
		kategorieID = if (lokalisierungFK == LokalisierungVon.KATEGORIE) spielelementID else null,
		kartentextID = if (lokalisierungFK == LokalisierungVon.KARTENTEXT) spielelementID else null
	)

	if (validateLokalisierung(lol)) {
		return lol
	} else {
		// @formatter:off
		error(
			"LokalisierungEntity ist nicht gültig - Fremdschlüssel nicht passend!\n" +
				"Das soll eine Lokalisierung von \"${lol.lokalisierungVon}\" sein, aber die Fremdschlüssel sind:\n" +
				"\tSpielID: ${lol.spielID}, KategorieID: ${lol.kategorieID}, KartentextID: ${lol.kartentextID}"
		)
		// @formatter:on
	}
}

private fun validateLokalisierung(entity: LokalisierungEntity): Boolean {
	return when (entity.lokalisierungVon) {
		LokalisierungVon.SPIEL -> entity.spielID != null && entity.kategorieID == null && entity.kartentextID == null
		LokalisierungVon.KATEGORIE -> entity.kategorieID != null && entity.spielID == null && entity.kartentextID == null
		LokalisierungVon.KARTENTEXT -> entity.kartentextID != null && entity.spielID == null && entity.kategorieID == null
	}
}
