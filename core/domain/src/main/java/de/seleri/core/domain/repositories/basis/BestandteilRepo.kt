package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil

interface BestandteilRepo<B: Bestandteil> {

	suspend fun getByIDs(ids: Collection<BestandteilID>): Collection<B>
	suspend fun getAktuelleByIDs(ids: Collection<BestandteilID>): Collection<B>
}
