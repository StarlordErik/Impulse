package de.seleri.core.domain.repositories

import de.seleri.core.domain.modell.ModellEntity

interface UpdateableRepo<E: ModellEntity> {

	suspend fun update(entity: E): Int
}
