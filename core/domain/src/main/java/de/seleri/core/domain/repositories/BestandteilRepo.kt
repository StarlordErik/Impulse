package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.BestandteilID
import de.seleri.core.domain.model.Bestandteil

interface BestandteilRepo<B: Bestandteil> {

	suspend fun getByID(id: BestandteilID): B?
	suspend fun getByIDs(ids: Collection<BestandteilID>): List<B>
}
