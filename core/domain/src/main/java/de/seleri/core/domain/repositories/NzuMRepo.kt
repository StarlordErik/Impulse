package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung

interface NzuMRepo<S: Sammlung, B: Bestandteil> {

	suspend fun insertVerbindung(sammlung: S, bestandteil: B)
	suspend fun deleteVerbindung(sammlung: S, bestandteil: B)
}
