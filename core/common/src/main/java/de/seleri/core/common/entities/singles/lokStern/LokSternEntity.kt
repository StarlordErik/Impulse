package de.seleri.core.common.entities.singles.lokStern

import de.seleri.core.common.entities.singles.SingleEntity
import de.seleri.core.common.ids.LokalisierungID

interface LokSternEntity: SingleEntity {

	val lokalisierungID: LokalisierungID
}
