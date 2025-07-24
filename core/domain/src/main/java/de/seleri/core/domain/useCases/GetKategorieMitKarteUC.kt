package de.seleri.core.domain.useCases

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.repositories.KartentextRepo

class GetKategorieMitKarteUC(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(
		anzahlTexte: Int, ausSammlung: Sammlung<Bestandteil>
	): Pair<Kategorie, List<Kartentext>> {
		var karte = ausSammlung.getKategorieMitKarte(anzahlTexte)
		if (karte.second.size < anzahlTexte) {
			kartentextRepo.update(ausSammlung.setAllKTungesehen())
			karte = ausSammlung.getKategorieMitKarte(anzahlTexte, karte.second)

			if (karte.second.size < anzahlTexte) {
				kartentextRepo.update(ausSammlung.setAllKTunbesprochen())
				karte = ausSammlung.getKategorieMitKarte(anzahlTexte, karte.second)
			}
		}

		karte.second.map { it.setKartentexteUngesehen() }
		kartentextRepo.update(karte.second)

		return karte
	}
}
