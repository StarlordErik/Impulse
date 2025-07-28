package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDaten

fun generiereSpielelementDaten(ogSprache: Sprache, lokalisierungEingabe: LokalisierungEingabe): SpielelementDaten =
	SpielelementDaten(lokalisierung = generiereLokalisierung(ogSprache, lokalisierungEingabe))

fun generiereSpielMetaDaten(ogSprache: Sprache, spielEingabe: SpielEingabe): SpielMetaDaten =
	SpielMetaDaten(spielelementDaten = generiereSpielelementDaten(ogSprache, spielEingabe.lokalisierungEingabe))
