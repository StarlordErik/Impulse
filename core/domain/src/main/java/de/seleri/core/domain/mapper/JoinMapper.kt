package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.joins.JoinEntity
import de.seleri.core.common.ids.spielelementID.BestandteilID
import de.seleri.core.common.ids.spielelementID.SammlungID
import de.seleri.core.domain.model.idEntity.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.idEntity.spielelemente.sammlungen.Sammlung

// @formatter:off
typealias JoinFactory<X, SID, BID> = (
	sammlungID: SID,
	bestandteilID: BID
) -> X

fun <
	E: JoinEntity<SID, BID>,
	SID: SammlungID,
	BID: BestandteilID,
	B: Bestandteil<BID>
	> Sammlung<B, SID>.toJoinEntity(
	factory: JoinFactory<E, SID, BID>
): Collection<E> =
	this.bestandteile.map { bestandteil ->
		factory(
			this.id, bestandteil.id
		)
	}
// @formatter:on
