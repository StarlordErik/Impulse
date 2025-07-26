package de.seleri.core.domain.model

import de.seleri.core.common.Sprache

val DEFAULT_SPRACHE = Sprache.OG
const val DEFAULT_BEARBEITET = false

data class Lokalisierung(
	private val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	val bezeichnung: String,
	val sprache: Sprache = DEFAULT_SPRACHE,
	val bearbeitet: Boolean = DEFAULT_BEARBEITET,
): DatenbankObjekt by datenbankObjektDaten {

	companion object {

		fun fromEingabe(
			id: Int = DEFAULT_ID,
			bezeichnung: String,
			sprache: Sprache = DEFAULT_SPRACHE,
			bearbeitet: Boolean = DEFAULT_BEARBEITET,
		): Lokalisierung =
			Lokalisierung(
				datenbankObjektDaten = DatenbankObjektDaten.fromEingabe(id),
				bezeichnung = bezeichnung,
				sprache = sprache,
				bearbeitet = bearbeitet
			)
	}
}
