package de.seleri.core.domain.repositories

import de.seleri.core.domain.modell.ModellEntity

interface EntityRepo<E: ModellEntity> {

	suspend fun delete(entity: E): Int
}
