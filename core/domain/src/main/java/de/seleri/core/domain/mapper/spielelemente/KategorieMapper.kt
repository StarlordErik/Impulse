package de.seleri.core.domain.mapper.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie

// @formatter:off
fun KategorieEntity.toDomain(lokalisierung: Lokalisierung, kartentexte: Collection<Kartentext>): Kategorie =
	Kategorie(
		spielelementDaten = SpielelementMapper(this, lokalisierung),
		bestandteile = kartentexte
	)

typealias KategorieFactory<K> = (
	lokalisierungID: LokalisierungID,
	selbstErstellt: Boolean,
	inaktiv: Boolean,
	favorisiert: Boolean,
) -> K

fun <E: KategorieEntity> Kategorie.toEntity(factory: KategorieFactory<E>): E =
	factory(
		this.lokalisierung.id,
		this.selbstErstellt,
		this.inaktiv,
		this.favorisiert
	)
// @formatter:on
