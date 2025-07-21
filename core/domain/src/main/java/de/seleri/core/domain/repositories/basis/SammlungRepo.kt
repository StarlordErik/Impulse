package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung

interface SammlungRepo<B: Bestandteil, S: Sammlung<B>> {

	suspend fun insertVerbindung(sammlung: S, bestandteil: B)
	suspend fun deleteVerbindung(sammlung: S, bestandteil: B)
}
