package de.seleri.core.repository.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.spielelemente.LokalisierungEntity
import de.seleri.core.data.entities.singles.SpielEntity
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaDaten
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt

object SpielMapper: SpielelementMapper<SpielelementID.SpielID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.SpielID, lokalisierung: Lokalisierung
	): LokalisierungEntity =
		lokalisierung.toEntityForSpiel(id)
}


fun Spiel.toEntity(): SpielEntity =
	SpielEntity(
		id = id,
		spielelementBasis = domainToSpielelmentBasis(this),
		anleitung = anleitung,
		texteProKarte = texteProKarte,
		bildDateiname = bildDateiname
	)


fun SpielEntity.toDomain(
	lokalisierungen: Collection<Lokalisierung>, kategorien: Collection<Kategorie>
): Spiel =
	Spiel(
		spielMetaDaten = entityToSpielMetaDaten(this, lokalisierungen),
		anleitung = anleitung,
		texteProKarte = texteProKarte,
		sammlungDaten = SammlungDaten(kategorien)
	)


fun SpielEntity.toMeta(
	lokalisierungen: Collection<Lokalisierung>
): SpielMetaObjekt =
	SpielMetaObjekt(
		spielMetaDaten = entityToSpielMetaDaten(this, lokalisierungen)
	)

private fun entityToSpielMetaDaten(entity: SpielEntity, lokalisierungen: Collection<Lokalisierung>): SpielMetaDaten =
	SpielMetaDaten(
		spielelementDaten = SpielelementDaten(
			DatenbankObjektDaten(id = entity.id),
			selbstErstellt = entity.spielelementBasis.selbstErstellt,
			inaktiv = entity.spielelementBasis.inaktiv,
			favorisiert = entity.spielelementBasis.favorisiert,
			ogSprache = entity.spielelementBasis.ogSprache,
			lokalisierungen = lokalisierungen
		), bildDateiname = entity.bildDateiname
	)
