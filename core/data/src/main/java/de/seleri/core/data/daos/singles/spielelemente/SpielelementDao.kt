package de.seleri.core.data.daos.singles.spielelemente

import de.seleri.core.data.daos.EntityDao
import de.seleri.core.data.entities.singles.spielelemente.SpielelementRoom

interface SpielelementDao<S: SpielelementRoom>: EntityDao<S> {
	suspend fun get(spielelementId: Int): S
}
