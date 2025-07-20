package de.seleri.core.repository.mapper

import de.seleri.core.data.entities.singles.KartentextEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.SpielelementDaten


fun KartentextEntity.toKartentext(
	lokalisierungIds: List<BestandteilID.LokalisierungID>
): Kartentext =
	// @formatter:off
	Kartentext(
		spielelementDaten = SpielelementDaten(
			datenbankObjektDaten = DatenbankObjektDaten(
				id = id
			),
			lokalisierungen = lokalisierungIds,
			ogSprache = spielelementBasis.ogSprache,
			selbstErstellt = spielelementBasis.selbstErstellt,
			inaktiv = spielelementBasis.inaktiv,
			favorisiert = spielelementBasis.favorisiert
		),
		gesehen = gesehen,
		besprochen = besprochen
	)
	// @formatter:on

fun Kartentext.toEntity(): KartentextEntity =
	// @formatter:off
	KartentextEntity(
		id = this.id,
		spielelementBasis = SpielelementBasis(
			ogSprache = this.spielelementDaten.ogSprache,
			selbstErstellt = this.spielelementDaten.selbstErstellt,
			inaktiv = this.spielelementDaten.inaktiv,
			favorisiert = this.spielelementDaten.favorisiert
		),
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)
	// @formatter:on
