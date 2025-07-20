package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.singles.KategorieEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteilTyp
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungsBestandteile

fun KategorieEntity.toDomain(
	lokalisierungen: Collection<BestandteilID.LokalisierungID>,
	kartentextIDs: Map<SammlungsBestandteilTyp, Collection<BestandteilID.KartentextID>>
): Kategorie =
	// @formatter:off
	Kategorie(
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
		sammlungsBestandteile = SammlungsBestandteile(kartentextIDs)
	)
	// @formatter:on

fun Kategorie.toEntity(): KategorieEntity =
	// @formatter:off
	KategorieEntity(
		id = spielelementDaten.datenbankObjektDaten.id,
		spielelementBasis = SpielelementBasis(
			ogSprache = spielelementDaten.ogSprache,
			selbstErstellt = spielelementDaten.selbstErstellt,
			inaktiv = spielelementDaten.inaktiv,
			favorisiert = spielelementDaten.favorisiert
		)
	)
	// @formatter:on

fun Kategorie.toJoinEntities(): Collection<KategorieXKartentext> =
	sammlungsBestandteile.bestandteileMap
		.flatMap { (_, ids) -> ids }
		.map { id ->
			// @formatter:off
			KategorieXKartentext(
				kategorieID = spielelementDaten.datenbankObjektDaten.id,
				kartentextID = id.toInt()
			)
			// @formatter:on
		}
