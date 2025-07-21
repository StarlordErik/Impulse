package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.model.spielelemente.Spielelement

interface SpielelementRepo<S: Spielelement> {

	/**
	 * @return generierte ID oder null bei nur-Update
	 */
	suspend fun upsert(spielelement: S): Integer?
	suspend fun delete(spielelement: S)
}
