package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.SammlungID

interface Join<SID: SammlungID, BID: BestandteilID> {

	val sammlungID: SID
	val bestandteilID: BID
}
