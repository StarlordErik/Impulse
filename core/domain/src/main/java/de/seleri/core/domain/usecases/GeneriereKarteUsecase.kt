package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.KategorieRepo

class GeneriereKarteUsecase(private val kategorieRepo: KategorieRepo, private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(kategorie: Kategorie, texteProKarte: Int): Collection<Kartentext> {
		TODO()
	}

	suspend operator fun invoke(
		spiel: Spiel,
		spielkategorien: Collection<Kategorie>,
	): Collection<Kartentext> {
		TODO()
	}

	private fun toKartentexte(kartentextIDs: Collection<BestandteilID.KartentextID>): Collection<Kartentext> {
		val kartentexte = kartentextRepo.getUngeseheneByIDs(kartentextIDs)

		kartentexte.map { kt -> kt.copy(gesehen = true) }
		kartentextRepo.upsert(kartentexte)

		return kartentexte
	}
}
