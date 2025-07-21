package de.seleri.core.domain.repositories.basis

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Spielelement

interface SpielelementRepo<S: Spielelement, ID: SpielelementID> {
	suspend fun upsert(spielelement: S)
	suspend fun delete(spielelement: S)

	suspend fun get(spielelementID: ID): S
}
