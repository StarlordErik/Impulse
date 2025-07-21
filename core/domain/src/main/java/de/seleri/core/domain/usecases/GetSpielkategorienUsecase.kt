package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.spiele.Spiel
import de.seleri.core.domain.model.spiele.spielelemente.Kategorie
import de.seleri.core.domain.repositories.KategorieRepo

class GetSpielkategorienUsecase(private val kategorieRepo: KategorieRepo) {

	suspend operator fun invoke(spiel: Spiel): Collection<Kategorie> {
		val kategorieIDs = spiel.sammlungDaten.bestandteile
		return kategorieRepo.getAktuelleByIDs(kategorieIDs)
	}
}
