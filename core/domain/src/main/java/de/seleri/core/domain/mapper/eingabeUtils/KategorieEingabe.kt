package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.KategorieIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun Kategorie.Companion.fromSkript(
	kategorieID: Int, lokalisierung: Lokalisierung?, kartentexte: List<Kartentext>?
): Kategorie? {
	if (lokalisierung == null) {
		if (kartentexte == null) {
			return null
		} else {
			// @formatter:off
			error("Die Kategorie-Lokalisierung darf nicht null sein, wenn es dazu Kartentexte gibt! Betroffen sind:\n" +
				"Kartentexte beginnend mit: ${kartentexte.first().lokalisierung.translationen.first()}")
			// @formatter:on
		}
	} else {
		if (kartentexte == null) {
			// @formatter:off
			error("Die Kartentexte dürfen nicht null sein, wenn es dazu eine Kategorie-Lokalisierung gibt! " +
				"Betroffen ist:\nKategorie: ${lokalisierung.translationen.first()}")
			// @formatter:on
		} else {
			return Kategorie(
				id = KategorieIDint(kategorieID),
				spielelementDaten = SpielelementDaten.fromSkript(lokalisierung),
				bestandteile = kartentexte
			)
		}
	}
}
