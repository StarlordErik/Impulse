package de.seleri.core.data.daos.spielelemente

import de.seleri.core.data.daos.DatenbankObjektDao
import de.seleri.core.data.entities.singles.spielelemente.SpielelementEntity

interface SpielelementDao<S: SpielelementEntity> : DatenbankObjektDao<S> {
	suspend fun get(spielelementId: Int): S
}
