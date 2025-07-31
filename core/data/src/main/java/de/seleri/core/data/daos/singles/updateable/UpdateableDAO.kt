package de.seleri.core.data.daos.singles.updateable

import de.seleri.core.data.daos.EntityDAO
import de.seleri.core.data.entities.LokSternRoom

interface UpdateableDAO<L: LokSternRoom>: EntityDAO<L> {

	suspend fun update(entity: L): Int
}
