package de.seleri.core.data.daos

import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.data.entities.singles.spielelemente.SpielelementEntity

interface SpielelementDao<S: SpielelementEntity> : DatenbankObjektDao<S> {
	suspend fun get(spielelementId: Int): S
}
