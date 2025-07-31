package de.seleri.core.common.entities.singles.lokStern

import de.seleri.core.common.entities.singles.SingleEntity
import de.seleri.core.common.id.LokalisierungID

interface LokSternEntity: SingleEntity {

	val lokalisierungID: LokalisierungID
}
