package de.seleri.core.data.daos.compositePk.joins

import de.seleri.core.common.ids.spielelementID.SammlungID
import de.seleri.core.data.daos.compositePk.CompositePkDAO
import de.seleri.core.data.entities.joins.JoinRoom

interface JoinDAO<J: JoinRoom, SID: SammlungID>: CompositePkDAO<J> {

	suspend fun getAll(sammlungID: SID): List<J>
}
