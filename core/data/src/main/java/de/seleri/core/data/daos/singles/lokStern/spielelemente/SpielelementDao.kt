package de.seleri.core.data.daos.singles.lokStern.spielelemente

import de.seleri.core.common.id.spielelementID.SpielelementID
import de.seleri.core.data.daos.singles.lokStern.LokSternDao
import de.seleri.core.data.entities.singles.lokStern.spielelemente.SpielelementRoom

interface SpielelementDao<S: SpielelementRoom, SID: SpielelementID>: LokSternDao<S> {

	suspend fun get(spielelementID: SID): S
}
