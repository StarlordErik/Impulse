package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.SpielEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.sammlungen.SammlungDaten
import de.seleri.core.domain.model.spiele.Spiel
import de.seleri.core.domain.model.spiele.spielelemente.SpielelementDaten

// @formatter:off
fun SpielEntity.toDomain(
	lokalisierungen: Collection<BestandteilID.LokalisierungID>,
	kategorieIDs: Collection<BestandteilID.KategorieID>
): Spiel =
	Spiel(
		spielelementDaten = SpielelementDaten(
			datenbankObjektDaten = DatenbankObjektDaten(
				id = id
			),
			lokalisierungen = lokalisierungen,
			ogSprache = spielelementBasis.ogSprache,
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert
		),
		sammlungDaten = SammlungDaten(kategorieIDs),
		anleitung = anleitung,
		texteProKarte = texteProKarte,
		bildDateiname = bildDateiname
	)
// @formatter:on

fun Spiel.toEntity(): SpielEntity =
	// @formatter:off
	SpielEntity(
		id = spielelementDaten.datenbankObjektDaten.id,
		spielelementBasis = SpielelementBasis(
			ogSprache = spielelementDaten.ogSprache,
			selbstErstellt = spielelementDaten.selbstErstellt,
			inaktiv = spielelementDaten.inaktiv,
			favorisiert = spielelementDaten.favorisiert
		),
		anleitung = anleitung,
		texteProKarte = texteProKarte,
		bildDateiname = bildDateiname
	)
// @formatter:on

fun Spiel.toJoinEntities(): Collection<SpielXKategorie> =
	sammlungDaten.bestandteile
		.map { id ->
			// @formatter:off
			SpielXKategorie(
				spielID = spielelementDaten.datenbankObjektDaten.id,
				kategorieID = id.toInt()
			)
			// @formatter:on
		}
