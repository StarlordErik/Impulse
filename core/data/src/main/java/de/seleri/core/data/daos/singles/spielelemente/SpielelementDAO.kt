package de.seleri.core.data.daos.singles.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.data.daos.UpdateableDAO
import de.seleri.core.data.daos.singles.SingleDAO
import de.seleri.core.data.entities.singles.spielelemente.SpielelementRoom

interface SpielelementDAO<S: SpielelementRoom, SID: SpielelementID>: SingleDAO<S>, UpdateableDAO<S> {

	suspend fun get(spielelementID: SID): S
}
