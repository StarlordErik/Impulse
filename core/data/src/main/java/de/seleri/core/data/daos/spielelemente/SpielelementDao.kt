package de.seleri.core.data.daos.spielelemente

import de.seleri.core.data.daos.DatenbankObjektDao
import de.seleri.core.data.entities.singles.spielelemente.SpielelementEntityRoom

interface SpielelementDao<S: SpielelementEntityRoom> : DatenbankObjektDao<S> {
	suspend fun get(spielelementId: Int): S
}
