package de.seleri.core.domain.repositories.base

import de.seleri.core.domain.modell.ModellEntity

interface EntityRepo<E: ModellEntity> {

	suspend fun new(entity: E): Int
	suspend fun delete(entity: E): Int
}
