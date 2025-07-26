package de.seleri.core.domain.model

import de.seleri.core.common.Sprache


data class Lokalisierung(
	private val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	val bezeichnung: String,
	val sprache: Sprache = Konstanten.SPRACHE,
	val bearbeitet: Boolean = Konstanten.BEARBEITET,
): DatenbankObjekt by datenbankObjektDaten {

	companion object {

		fun fromEingabe(
			bezeichnung: String,
			id: Int = Konstanten.ID,
			sprache: Sprache = Konstanten.SPRACHE,
			bearbeitet: Boolean = Konstanten.BEARBEITET,
		): Lokalisierung =
			Lokalisierung(
				datenbankObjektDaten = DatenbankObjektDaten.fromEingabe(id),
				bezeichnung = bezeichnung,
				sprache = sprache,
				bearbeitet = bearbeitet
			)
	}
}
