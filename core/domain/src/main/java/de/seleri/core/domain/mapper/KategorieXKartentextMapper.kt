package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.joins.KategorieXKartentextEntity
import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.domain.modell.spielelemente.Kategorie

// @formatter:off
typealias KategorieXKartentextFactory<X> = (
	kategorieID: KategorieID,
	kartentextID: KartentextID
) -> X

fun <E: KategorieXKartentextEntity> Kategorie.toEntity(factory: KategorieXKartentextFactory<E>): Collection<E> =
	this.bestandteile.map { kartentext ->
		factory(
			this.id,
			kartentext.id
		)
	}
// @formatter:on
