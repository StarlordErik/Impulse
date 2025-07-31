package de.seleri.core.data.daos.singles.updateable.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.data.daos.singles.updateable.UpdateableDAO
import de.seleri.core.data.entities.singles.spielelemente.SpielelementRoom

interface SpielelementDAO<S: SpielelementRoom, SID: SpielelementID>: UpdateableDAO<S> {

	suspend fun get(spielelementID: SID): S
}
