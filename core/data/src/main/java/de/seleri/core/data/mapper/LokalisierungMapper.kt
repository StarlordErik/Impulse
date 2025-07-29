package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntityRoom
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung

fun LokalisierungEntityRoom.toDomain() =
	Lokalisierung(
		entityModellDaten = EntityModellDaten(id = id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet
	)

fun Lokalisierung.toNullEntity() =
	LokalisierungEntityRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null,
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForSpiel(spielID: SpielelementID.SpielID) =
	LokalisierungEntityRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache, bearbeitet = bearbeitet, spielID = spielID.toInt(),
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForKategorie(kategorieID: SpielelementID.KategorieID) =
	LokalisierungEntityRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet, spielID = null, kategorieID = kategorieID.toInt(),
		kartentextID = null
	)

fun Lokalisierung.toEntityForKartentext(kartentextID: SpielelementID.KartentextID) =
	LokalisierungEntityRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null, kategorieID = null, kartentextID = kartentextID.toInt()
	)
