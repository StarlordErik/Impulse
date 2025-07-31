package de.seleri.core.domain.deprecatedRepositories

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.deprecatedRepositories.basis.SpielelementRepo

interface KartentextRepo: SpielelementRepo<Kartentext, SpielelementID.KartentextID> {

	suspend fun update(kartentexte: Collection<Kartentext>)
}
