package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.joins.SpielXKategorieEntity
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel

// @formatter:off
typealias SpielXKategorieFactory<X> = (
	spielID: SpielID,
	kategorieID: KategorieID
) -> X

fun <E: SpielXKategorieEntity> Spiel.toEntity(factory: SpielXKategorieFactory<E>): Collection<E> =
	this.bestandteile.map { kategorie ->
		factory(
			this.id,
			kategorie.id
		)
	}
// @formatter:on
