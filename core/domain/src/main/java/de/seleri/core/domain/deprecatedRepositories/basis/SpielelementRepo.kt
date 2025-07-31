package de.seleri.core.domain.deprecatedRepositories.basis

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielelementRepo<S: Spielelement<ID>, ID: SpielelementID> {
	suspend fun upsert(spielelement: S)
	suspend fun delete(spielelement: S)

	suspend fun get(spielelementID: ID): S
}
