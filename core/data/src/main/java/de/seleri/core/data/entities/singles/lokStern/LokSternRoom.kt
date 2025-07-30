package de.seleri.core.data.entities.singles.lokStern

import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.data.entities.singles.SingleRoom

interface LokSternRoom: SingleRoom {

	val lokalisierungID: LokalisierungID
}
