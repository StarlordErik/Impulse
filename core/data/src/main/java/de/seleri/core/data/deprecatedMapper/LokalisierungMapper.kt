package de.seleri.core.data.deprecatedMapper/*
import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung

fun LokalisierungRoom.toDomain() =
	Lokalisierung(
		entityModellDaten = EntityModellDaten(id = id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet
	)

fun Lokalisierung.toNullEntity() =
	LokalisierungRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null,
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForSpiel(spielID: SpielelementID.SpielID) =
	LokalisierungRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache, bearbeitet = bearbeitet, spielID = spielID.toInt(),
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForKategorie(kategorieID: SpielelementID.KategorieID) =
	LokalisierungRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet, spielID = null, kategorieID = kategorieID.toInt(),
		kartentextID = null
	)

fun Lokalisierung.toEntityForKartentext(kartentextID: SpielelementID.KartentextID) =
	LokalisierungRoom(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null, kategorieID = null, kartentextID = kartentextID.toInt()
	)


 */
