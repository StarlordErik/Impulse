package de.seleri.core.domain.mapper.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext

// @formatter:off
fun KartentextEntity.toDomain(lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		spielelementDaten = SpielelementMapper(this, lokalisierung),
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)

typealias KartentextFactory<K> = (
	lokalisierungID: LokalisierungID,
	selbstErstellt: Boolean,
	inaktiv: Boolean,
	favorisiert: Boolean,
	gesehen: Boolean,
	besprochen: Boolean
	) -> K

fun <E: KartentextEntity> Kartentext.toEntity(factory: KartentextFactory<E>): E =
	factory(
		this.lokalisierung.id,
		this.selbstErstellt,
		this.inaktiv,
		this.favorisiert,
		this.gesehen,
		this.besprochen
	)
// @formatter:on
