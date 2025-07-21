package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.repositories.KartentextRepo

class GetKarteUsecase(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(sammlung: Sammlung, texteProKarte: Int): Collection<Kartentext> {
		val kartentexteIDs = sammlung.karte(texteProKarte)
		val kartentexte = kartentextRepo.getUngeseheneByIDs(kartentexteIDs)

		kartentexte.map { kt -> kt.copy(gesehen = true) }
		kartentextRepo.upsert(kartentexte)

		return kartentexte
	}
}
