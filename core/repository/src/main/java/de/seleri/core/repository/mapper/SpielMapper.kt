package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.SpielEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Spiel
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteilTyp
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

fun SpielEntity.toDomain(
	lokalisierungen: Collection<BestandteilID.LokalisierungID>,
	kategorieIDs: Map<SammlungsBestandteilTyp, Collection<BestandteilID.KategorieID>>
): Spiel =
	// @formatter:off
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
		sammlungsBestandteile = SammlungsBestandteile(kategorieIDs),
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
		texteProKarte = texteProKarte,
		bildDateiname = bildDateiname
	)
// @formatter:on

fun Spiel.toJoinEntities(): Collection<SpielXKategorie> =
	sammlungsBestandteile.bestandteileMap
		.flatMap { (_, ids) -> ids }
		.map { id ->
			// @formatter:off
			SpielXKategorie(
				spielID = spielelementDaten.datenbankObjektDaten.id,
				kategorieID = id.toInt()
			)
			// @formatter:on
		}
