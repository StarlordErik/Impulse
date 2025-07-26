package de.seleri.core.domain.useCases

import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Karte
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.repositories.KartentextRepo

class GetKarteUC(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(
		anzahlTexte: Int, ausSammlung: Sammlung<Bestandteil>
	): Karte {
		var karte = ausSammlung.getKarte(anzahlTexte)
		if (karte.anzahlTexte() < anzahlTexte) {
			kartentextRepo.update(ausSammlung.setAllKTungesehen())
			karte = ausSammlung.getKarte(anzahlTexte, karte.kartentexte)

			if (karte.anzahlTexte() < anzahlTexte) {
				kartentextRepo.update(ausSammlung.setAllKTunbesprochen())
				karte = ausSammlung.getKarte(anzahlTexte, karte.kartentexte)
			}
		}

		karte.kartentexte.map { it.setKartentexteUngesehen() }
		kartentextRepo.update(karte.kartentexte)

		return karte
	}
}
