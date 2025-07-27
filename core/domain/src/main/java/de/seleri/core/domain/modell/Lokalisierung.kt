package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache


data class Lokalisierung(
	private val datenbankObjektDaten: EntityModellDaten = EntityModellDaten(),

	val bezeichnung: String,
	val sprache: Sprache = Konstanten.SPRACHE,
	val bearbeitet: Boolean = Konstanten.BEARBEITET,
): EntityModell by datenbankObjektDaten {

	companion object {

		fun fromEingabe(
			bezeichnung: String,
			id: Int = Konstanten.ID,
			sprache: Sprache = Konstanten.SPRACHE,
			bearbeitet: Boolean = Konstanten.BEARBEITET,
		): Lokalisierung =
			Lokalisierung(
				datenbankObjektDaten = EntityModellDaten.fromEingabe(id),
				bezeichnung = bezeichnung,
				sprache = sprache,
				bearbeitet = bearbeitet
			)
	}
}
