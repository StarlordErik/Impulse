package de.seleri.core.domain.repositories.idEntity.spielelemente

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext

interface KartentextRepo: SpielelementRepo<Kartentext, KartentextID> {

	suspend fun updateAll(kartentexte: Collection<Kartentext>)
}
