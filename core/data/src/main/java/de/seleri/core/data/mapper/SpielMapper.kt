package de.seleri.core.data.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielEntityRoom
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDaten
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaObjekt

object SpielMapper: SpielelementMapper<SpielelementID.SpielID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.SpielID, lokalisierung: Lokalisierung
	): LokalisierungEntityRoom =
		lokalisierung.toEntityForSpiel(id)
}


fun Spiel.toEntity(): SpielEntityRoom =
	SpielEntityRoom(
		id = id,
		spielelementBasis = domainToSpielelmentBasis(this),
		anleitung = anleitung,
		texteProKarte = texteProKarte,
		bildDateiname = bildDateiname
	)


fun SpielEntityRoom.toDomain(
	lokalisierungen: Collection<Lokalisierung>, kategorien: Collection<Kategorie>
): Spiel =
	Spiel(
		spielMetaDaten = entityToSpielMetaDaten(this, lokalisierungen),
		anleitung = anleitung,
		texteProKarte = texteProKarte, bestandteile = kategorien
	)


fun SpielEntityRoom.toMeta(
	lokalisierungen: Collection<Lokalisierung>
): SpielMetaObjekt =
	SpielMetaObjekt(
		spielMetaDaten = entityToSpielMetaDaten(this, lokalisierungen)
	)

private fun entityToSpielMetaDaten(entity: SpielEntityRoom, lokalisierungen: Collection<Lokalisierung>): SpielMetaDaten =
	SpielMetaDaten(
		spielelementDaten = SpielelementDaten(
			EntityModellDaten(id = entity.id),
			selbstErstellt = entity.spielelementBasis.selbstErstellt,
			inaktiv = entity.spielelementBasis.inaktiv,
			favorisiert = entity.spielelementBasis.favorisiert,
			ogSprache = entity.spielelementBasis.ogSprache, lokalisierung = lokalisierungen
		), bildDateiname = entity.bildDateiname
	)
