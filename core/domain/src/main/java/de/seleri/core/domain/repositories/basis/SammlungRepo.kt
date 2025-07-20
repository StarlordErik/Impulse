package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung

interface SammlungRepo<S: Sammlung, B: Bestandteil, ID: BestandteilID> {

	suspend fun insertVerbindung(sammlung: S, bestandteil: B)
	suspend fun deleteVerbindung(sammlung: S, bestandteil: B)

	suspend fun getSammlungDaten(): Collection<ID>
}
