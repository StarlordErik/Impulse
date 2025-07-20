package de.seleri.core.repository.implementations

import de.seleri.core.data.daos.KartentextDao
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.KartentextRepo

class KartentextImpl(
	private val dao: KartentextDao
): KartentextRepo {

	override suspend fun upsert(kartentext: Kartentext) {
		TODO("Not yet implemented")
	}

	override suspend fun delete(kartentext: Kartentext) {
		TODO("Not yet implemented")
	}
}
