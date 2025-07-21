package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung

fun LokalisierungEntity.toDomain() =
	Lokalisierung(
		datenbankObjektDaten = DatenbankObjektDaten(id = id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet
	)

fun Lokalisierung.toEntityForSpiel(spielID: Int) =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet, spielID = spielID,
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForKategorie(kategorieID: Int) =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null, kategorieID = kategorieID,
		kartentextID = null
	)

fun Lokalisierung.toEntityForKartentext(kartentextID: Int) =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null,
		kategorieID = null, kartentextID = kartentextID
	)
