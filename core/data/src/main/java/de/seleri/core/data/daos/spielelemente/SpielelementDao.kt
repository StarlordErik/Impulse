package de.seleri.core.data.daos.spielelemente

import de.seleri.core.data.daos.DatenbankObjektDao
import de.seleri.core.data.entities.singles.spielelemente.SpielelementRoom

interface SpielelementDao<S: SpielelementRoom>: DatenbankObjektDao<S> {
	suspend fun get(spielelementId: Int): S
}
