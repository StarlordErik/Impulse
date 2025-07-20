package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.domain.model.DatenbankEintragsDaten
import de.seleri.core.domain.model.Lokalisierung

fun LokalisierungEntity.toDomain(): Lokalisierung {
	val spielelementID = spielID
		?: kategorieID
		?: kartentextID
		?: error("LokalisierungEntity besitzt keinen Fremdschlüssel - weder für Spiel, Kategorie noch Kartentext!")

	return Lokalisierung(
		datenbankEintragsDaten = DatenbankEintragsDaten(id),
		bezeichnung = bezeichnung,
		sprache = sprache,
		bearbeitet = bearbeitet,
		spielelementID = spielelementID
	)
}

fun Lokalisierung.toSpielEntity(): LokalisierungEntity =
	toEntityWithId(spielId = this.spielelementID)

fun Lokalisierung.toKategorieEntity(): LokalisierungEntity =
	toEntityWithId(kategorieId = this.spielelementID)

fun Lokalisierung.toKartentextEntity(): LokalisierungEntity =
	toEntityWithId(kartentextId = this.spielelementID)

private fun Lokalisierung.toEntityWithId(
	spielId: Int? = null, kategorieId: Int? = null, kartentextId: Int? = null
): LokalisierungEntity =
	LokalisierungEntity(
		id = this.id,
		bezeichnung = this.bezeichnung,
		sprache = this.sprache,
		bearbeitet = this.bearbeitet,
		spielID = spielId,
		kategorieID = kategorieId,
		kartentextID = kartentextId
	)
