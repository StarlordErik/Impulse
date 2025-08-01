package de.seleri.core.data.daos.relations

import de.seleri.core.common.ids.spielelementID.SammlungID
import de.seleri.core.data.entities.singles.spielelemente.BestandteilRoom

interface RelationDAO<SID: SammlungID, B: BestandteilRoom> {

	suspend fun getAllBestandteile(sammlungID: SID): List<B>
}
