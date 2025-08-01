package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.domain.modell.idEntity.Lokalisierung
import de.seleri.core.domain.modell.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.modell.idEntity.spielelemente.SpielelementDO

fun Kartentext.Companion.fromSkript(kartentextID: Int, lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		id = KartentextID(kartentextID),
		spielelementDaten = SpielelementDO(lokalisierung),
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
