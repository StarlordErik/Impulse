package de.seleri.core.data.daos.joins

import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.SammlungID
import de.seleri.core.data.daos.EntityDao
import de.seleri.core.data.entities.joins.JoinRoom

interface JoinDao<J: JoinRoom<SID, BID>, SID: SammlungID, BID: BestandteilID>: EntityDao<J> {

	suspend fun getAllForSammlung(sammlungID: SID): List<BID>
}
