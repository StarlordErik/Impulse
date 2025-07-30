package de.seleri.core.data.daos.joins.relations

import de.seleri.core.common.id.spielelementID.SammlungID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.BestandteilRoom

interface RelationDAO<SID: SammlungID, B: BestandteilRoom> {

	suspend fun getAll(sammlungID: SID): List<B>
}
