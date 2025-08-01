package de.seleri.core.data.daos

import de.seleri.core.data.entities.LokSternRoom

interface UpdateableDAO<L: LokSternRoom>: EntityDAO<L> {

	suspend fun update(entity: L): Int
}
