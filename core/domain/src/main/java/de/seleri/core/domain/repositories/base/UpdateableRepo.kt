package de.seleri.core.domain.repositories.base

import de.seleri.core.domain.modell.ModellEntity

interface UpdateableRepo<E: ModellEntity>: EntityRepo<E> {

	suspend fun update(entity: E): Int
}
