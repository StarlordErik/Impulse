package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.ModelEntity

interface UpdateableRepo<E: ModelEntity> {

	suspend fun update(model: E): Int
}
