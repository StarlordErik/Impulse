package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.model.idEntity.Lokalisierung
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.model.idEntity.spielelemente.SpielelementDO
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO

fun Spiel.Companion.fromSkript(spielID: Int, lokalisierung: Lokalisierung?, kategorien: List<Kategorie>?): Spiel {
	if (lokalisierung == null) {
		if (kategorien == null) {
			// @formatter:off
			error("Was versuchst du hier überhaupt?\n" +
				"Sowohl die Spiel-Lokalisierung als auch die Kategorien sind null? Verschwende keinen Strom."
			)
			// @formatter:on
		} else {
			// @formatter:off
			error("Die Spiel-Lokalisierung darf nicht null sein, wenn es dazu Kategorien gibt! Betroffen sind:\n" +
				"Kategorien beginnend mit: ${kategorien.first().lokalisierung.translationen.first()}")
			// @formatter:on
		}
	} else {
		if (kategorien == null) {
			// @formatter:off
			error("Die Kategorien dürfen nicht null sein, wenn es dazu eine Spiel-Lokalisierung gibt! " +
				"Betroffen ist:\nSpiel: ${lokalisierung.translationen.first()}")
			// @formatter:on
		} else {
			return Spiel(
				id = SpielID(spielID), spielMetaDaten = SpielMetaDO(
					spielelementDaten = SpielelementDO(lokalisierung)
				), bestandteile = kategorien
			)
		}
	}
}
