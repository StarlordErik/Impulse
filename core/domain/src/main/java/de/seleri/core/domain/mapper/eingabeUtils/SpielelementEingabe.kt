package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun generiereSpielelementDaten(ogSprache: Sprache, lokalisierungEingabe: LokalisierungEingabe): SpielelementDaten =
	SpielelementDaten(lokalisierung = generiereLokalisierung(ogSprache, lokalisierungEingabe))
