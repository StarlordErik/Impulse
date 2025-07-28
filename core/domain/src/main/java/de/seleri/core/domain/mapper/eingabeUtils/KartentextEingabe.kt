package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.KartentextIDint
import de.seleri.core.domain.modell.spielelemente.Kartentext

data class KartentextEingabe(
	val kartentextIDint: KartentextIDint, val lokalisierung: LokalisierungEingabe,
)

fun generiereKartentext(ogSprache: Sprache, kartentextEingabe: KartentextEingabe): Kartentext =
	Kartentext(
		id = kartentextEingabe.kartentextIDint,
		spielelementDaten = generiereSpielelementDaten(ogSprache, kartentextEingabe.lokalisierung),
	)
