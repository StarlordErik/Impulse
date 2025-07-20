package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.ids.BestandteilID

interface BestandteilRepo<B: Bestandteil> {

	suspend fun getByIDs(ids: Collection<BestandteilID>): List<B>
}
