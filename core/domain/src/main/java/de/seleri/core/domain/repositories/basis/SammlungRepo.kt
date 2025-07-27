package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.modell.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung

interface SammlungRepo<B: Bestandteil, S: Sammlung<B>> {

	suspend fun insertConnection(sammlung: S, bestandteil: B)

	suspend fun deleteConnection(sammlung: S, bestandteil: B)
	suspend fun updateConnections(sammlung: S, neueBestandteile: Collection<B>)
}
