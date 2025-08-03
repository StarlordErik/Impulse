package de.seleri.core.domain.repositories.idEntity.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext

interface KartentextRepo: BestandteilRepo<Kartentext, KartentextEntity, KartentextID> {

	suspend fun updateAll(kartentexte: Collection<Kartentext>): Int
}
