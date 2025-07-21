package de.seleri.core.domain.repositories.basis

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.ids.SammlungID
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung

interface SammlungRepo<S: Sammlung, SID: SammlungID, B: Bestandteil, BID: BestandteilID> {

	suspend fun insertVerbindung(sammlung: S, bestandteil: B)
	suspend fun deleteVerbindung(sammlung: S, bestandteil: B)

	suspend fun getSammlungDaten(sammlungID: SID): Collection<BID>
}
