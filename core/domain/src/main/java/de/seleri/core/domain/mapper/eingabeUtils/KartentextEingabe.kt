package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.KartentextIDint
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun Kartentext.Companion.fromSkript(kartentextID: Int, lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		id = KartentextIDint(kartentextID),
		spielelementDaten = SpielelementDaten.fromSkript(lokalisierung),
	)
