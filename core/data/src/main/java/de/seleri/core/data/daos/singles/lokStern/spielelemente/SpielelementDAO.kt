package de.seleri.core.data.daos.singles.lokStern.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.data.daos.singles.lokStern.LokSternDAO
import de.seleri.core.data.entities.singles.lokStern.spielelemente.SpielelementRoom

interface SpielelementDAO<S: SpielelementRoom, SID: SpielelementID>: LokSternDAO<S> {

	suspend fun get(spielelementID: SID): S
}
