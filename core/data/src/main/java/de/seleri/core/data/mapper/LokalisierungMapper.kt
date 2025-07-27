package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung

fun LokalisierungEntity.toDomain() =
	Lokalisierung(
		datenbankObjektDaten = EntityModellDaten(id = id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet
	)

fun Lokalisierung.toNullEntity() =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null,
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForSpiel(spielID: SpielelementID.SpielID) =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache, bearbeitet = bearbeitet, spielID = spielID.toInt(),
		kategorieID = null,
		kartentextID = null
	)

fun Lokalisierung.toEntityForKategorie(kategorieID: SpielelementID.KategorieID) =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet, spielID = null, kategorieID = kategorieID.toInt(),
		kartentextID = null
	)

fun Lokalisierung.toEntityForKartentext(kartentextID: SpielelementID.KartentextID) =
	LokalisierungEntity(
		id = id,
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielID = null, kategorieID = null, kartentextID = kartentextID.toInt()
	)
