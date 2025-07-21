package de.seleri.core.repository.mapper

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.SpielEntity
import de.seleri.core.data.entities.singles.SpielelementBasis
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.SpielelementDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaDaten

object SpielMapper: SpielelementMapper<SpielelementID.SpielID> {

	override fun lokalisierungToEntity(
		id: SpielelementID.SpielID, lokalisierung: Lokalisierung
	): LokalisierungEntity {
		return lokalisierung.toEntityForSpiel(id)
	}
}

fun Spiel.toEntity(): SpielEntity {
	return SpielEntity(
		id = id, spielelementBasis = SpielelementBasis(
			selbstErstellt = selbstErstellt, inaktiv = inaktiv, favorisiert = favorisiert, ogSprache = ogSprache
		), anleitung = anleitung, texteProKarte = texteProKarte, bildDateiname = bildDateiname
	)
}

fun SpielEntity.toDomain(
	lokalisierungen: Collection<Lokalisierung>, kategorien: Collection<Kategorie>
): Spiel {
	return Spiel(
		spielMetaDaten = SpielMetaDaten(
			spielelementDaten = SpielelementDaten(
				DatenbankObjektDaten(id = id),
				selbstErstellt = spielelementBasis.selbstErstellt,
				inaktiv = spielelementBasis.inaktiv,
				favorisiert = spielelementBasis.favorisiert,
				ogSprache = spielelementBasis.ogSprache,
				lokalisierungen = lokalisierungen
			), bildDateiname = bildDateiname
		), anleitung = anleitung, texteProKarte = texteProKarte, sammlungDaten = SammlungDaten(kategorien)
	)
}
