package de.seleri.core.domain.repositories.idEntity.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.domain.model.idEntity.spielelemente.Spielelement
import de.seleri.core.domain.repositories.idEntity.IDentityRepo

interface SpielelementRepo<S: Spielelement<SID>, SID: SpielelementID>: IDentityRepo<S, SID> {

	suspend fun update(model: S): Int

	suspend fun find(id: SID): S?
}
