package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.common.idInt.KartentextIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun Kartentext.Companion.fromSkript(kartentextID: Int, lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		id = KartentextIDint(kartentextID),
		spielelementDaten = SpielelementDaten.fromSkript(lokalisierung),
	)

fun Kartentext.Companion.fromSkriptForAll(
	kartentextID: Int, lokalisierungen: List<Lokalisierung>?
): Pair<List<Kartentext>?, Int> {
	var anzahlNeuerKartentexte = 0

	val kartentexte = lokalisierungen?.map { lokalisierung ->
		val neueID = kartentextID + anzahlNeuerKartentexte++
		Kartentext.fromSkript(neueID, lokalisierung)
	}

	val maxID = anzahlNeuerKartentexte - 1

	return if (kartentexte == null) {
		null to maxID
	} else {
		Pair(kartentexte, maxID)
	}
}
