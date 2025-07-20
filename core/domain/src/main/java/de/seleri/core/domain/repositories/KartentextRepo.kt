package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kartentext

interface KartentextRepo {

	suspend fun upsert(kartentext: Kartentext)
	suspend fun delete(kartentext: Kartentext)
}
