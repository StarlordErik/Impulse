package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.common.idInt.SpielIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDaten

fun Spiel.Companion.fromSkript(spielID: Int, lokalisierung: Lokalisierung?, kategorien: List<Kategorie?>): Spiel {
	val echteKategorien = kategorien.filterNotNull()

	if (lokalisierung == null) {
		if (echteKategorien.isEmpty()) {
			// @formatter:off
			error("Was versuchst du hier überhaupt?\n" +
				"Die Spiel-Lokalisierung ist null und die Kategorien leer? Verschwende keinen Strom."
			)
			// @formatter:on
		} else {
			// @formatter:off
			error("Die Spiel-Lokalisierung darf nicht null sein, wenn es dazu Kategorien gibt! Betroffen sind:\n" +
				"Kategorien beginnend mit: ${echteKategorien.first().lokalisierung.translationen.first()}")
			// @formatter:on
		}
	} else {
		if (echteKategorien.isEmpty()) {
			// @formatter:off
			error("Die Kategorien dürfen nicht leer sein, wenn es dazu eine Spiel-Lokalisierung gibt! " +
				"Betroffen ist:\nSpiel: ${lokalisierung.translationen.first()}")
			// @formatter:on
		} else {
			return Spiel(
				id = SpielIDint(spielID), spielMetaDaten = SpielMetaDaten(
					spielelementDaten = SpielelementDaten(lokalisierung)
				), bestandteile = echteKategorien
			)
		}
	}
}
