package de.seleri.core.domain.useCases

import de.seleri.core.domain.modell.spielelemente.sammlungen.Karte
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.repositories.KartentextRepo

class GetKarteUC(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(
		anzahlTexte: Int, ausSammlung: Sammlung<*, *>
	): Karte {
		var karte = ausSammlung.getKarte(anzahlTexte)
		if (karte.anzahlTexte() < anzahlTexte) {
			kartentextRepo.updateAll(ausSammlung.setAllKTungesehen())
			karte = ausSammlung.getKarte(anzahlTexte, karte.kartentexte)

			if (karte.anzahlTexte() < anzahlTexte) {
				kartentextRepo.updateAll(ausSammlung.setAllKTunbesprochen())
				karte = ausSammlung.getKarte(anzahlTexte, karte.kartentexte)
			}
		}

		karte.kartentexte.map { it.setKartentexteUngesehen() }
		kartentextRepo.updateAll(karte.kartentexte)

		return karte
	}
}
