package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.basis.SpielelementRepo

interface KartentextRepo: SpielelementRepo<Kartentext, SpielelementID.KartentextID> {

	suspend fun update(kartentexte: Collection<Kartentext>)
}
