package de.seleri.core.data.entities.joins

import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.SammlungID
import de.seleri.core.data.entities.EntityRoom

interface JoinRoom<SID: SammlungID, BID: BestandteilID>: EntityRoom {

	val sammlungID: SID

	val bestandteilID: BID
}
