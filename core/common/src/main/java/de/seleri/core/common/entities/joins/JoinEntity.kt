package de.seleri.core.common.entities.joins

import de.seleri.core.common.entities.Entity
import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.SammlungID

interface JoinEntity<SID: SammlungID, BID: BestandteilID>: Entity {

	val sammlungID: SID
	val bestandteilID: BID
}
