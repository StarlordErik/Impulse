package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.Spiel
import de.seleri.core.domain.repositories.KartentextRepo

class GetKarteUsecase(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(kategorie: Kategorie, texteProKarte: Int): Collection<Kartentext> {
		val kartentexteIDs = kategorie.karte(texteProKarte)
			?: run {
				kartentextRepo.setUngesehen(kategorie.sammlungDaten.bestandteile)
				kategorie.karte(texteProKarte)!!
			}
		val kartentexte = kartentextRepo.getUngeseheneByIDs(kartentexteIDs)

		kartentexte.map { kt -> kt.copy(gesehen = true) }
		kartentextRepo.upsert(kartentexte)

		return kartentexte
	}

	suspend operator fun invoke(
		spiel: Spiel,
		spielkategorien: Collection<Kategorie>,
	): Collection<Kartentext> {
		val texteProKarte = spiel.texteProKarte
		val kartentexteIDs = spiel.karte(texteProKarte)
			?: run {
				val kartentextIDs = spielkategorien.flatMap { it.sammlungDaten.bestandteile }
				kartentextRepo.setUngesehen(kartentextIDs)

				spiel.karte(texteProKarte)!!
			}
		val kartentexte = kartentextRepo.getUngeseheneByIDs(kartentexteIDs)

		kartentexte.map { kt -> kt.copy(gesehen = true) }
		kartentextRepo.upsert(kartentexte)

		return kartentexte
	}
}
