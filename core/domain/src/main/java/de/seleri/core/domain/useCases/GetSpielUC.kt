package de.seleri.core.domain.useCases

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.modell.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class GetSpielUC(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(spielID: SpielID): Spiel =
		spielRepo.get(spielID)
}
