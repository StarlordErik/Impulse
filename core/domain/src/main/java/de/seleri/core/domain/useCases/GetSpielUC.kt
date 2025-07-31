package de.seleri.core.domain.useCases

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.domain.deprecatedRepositories.SpielRepo

class GetSpielUC(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(spielID: SpielelementID.SpielID): Spiel =
		spielRepo.get(spielID)
}
