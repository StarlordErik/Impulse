package de.seleri.core.domain.repositories.idEntity.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.BestandteilEntity
import de.seleri.core.common.ids.spielelementID.BestandteilID
import de.seleri.core.domain.model.idEntity.spielelemente.sammlungen.Bestandteil

interface BestandteilRepo<B: Bestandteil<BID>, E: BestandteilEntity, BID: BestandteilID>: SpielelementRepo<B, BID> {

	suspend fun complete(bestandteilEntities: Collection<E>): Collection<B>
}
