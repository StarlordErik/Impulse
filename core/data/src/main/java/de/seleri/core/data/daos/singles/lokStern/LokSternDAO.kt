package de.seleri.core.data.daos.singles.lokStern

import de.seleri.core.data.daos.EntityDAO
import de.seleri.core.data.entities.singles.lokStern.LokSternRoom

interface LokSternDAO<L: LokSternRoom>: EntityDAO<L> {

	suspend fun update(entity: L): Int
}
