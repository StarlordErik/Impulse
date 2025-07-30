package de.seleri.core.data.daos.singles.lokStern

import de.seleri.core.data.daos.EntityDao
import de.seleri.core.data.entities.singles.lokStern.LokSternRoom

interface LokSternDao<L: LokSternRoom>: EntityDao<L> {

	suspend fun update(entity: L): Int
}
