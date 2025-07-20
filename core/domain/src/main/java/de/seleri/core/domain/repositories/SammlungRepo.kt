package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Bestandteil

interface SammlungRepo<B: Bestandteil> {

	suspend fun insertBestandteil(bestandteil: B)
	suspend fun deleteBestandteil(bestandteil: B)
}
