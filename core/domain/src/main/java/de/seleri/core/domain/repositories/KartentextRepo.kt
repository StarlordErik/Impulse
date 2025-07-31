package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.repositories.base.SpielelementRepo

interface KartentextRepo: SpielelementRepo<Kartentext, KartentextID> {

	suspend fun updateAll(kartentexte: Collection<Kartentext>)
}
