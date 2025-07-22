package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.spielelemente.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.idTypes.SpielelementID

fun LokalisierungEntity.toDomain() =
	Lokalisierung(
		datenbankObjektDaten = DatenbankObjektDaten(id = id),
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
