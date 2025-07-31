package de.seleri.core.domain.deprecatedRepositories.basis

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielelementRepo<S: Spielelement, ID: SpielelementID> {
	suspend fun upsert(spielelement: S)
	suspend fun delete(spielelement: S)

	suspend fun get(spielelementID: ID): S
}
