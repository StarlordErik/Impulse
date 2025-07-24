package de.seleri.core.domain.useCases

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.repositories.KartentextRepo

class GetKarteUC(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(anzahlTexte: Int, ausSammlung: Sammlung<Bestandteil>): List<Kartentext> {

		var karte = ausSammlung.getKarte(anzahlTexte)
		if (karte.size < anzahlTexte) {
			kartentextRepo.update(ausSammlung.setAllKTungesehen())
			karte = ausSammlung.getKarte(anzahlTexte, karte)

			if (karte.size < anzahlTexte) {
				kartentextRepo.update(ausSammlung.setAllKTunbesprochen())
				karte = ausSammlung.getKarte(anzahlTexte, karte)
			}
		}

		karte.map { it.setKartentexteUngesehen() }
		kartentextRepo.update(karte)

		return karte
	}
}
