package de.seleri.core.tools.fromSkriptUtils

import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDaten

fun SpielelementDaten.Companion.fromSkript(lokalisierung: Lokalisierung): SpielelementDaten =
	SpielelementDaten(lokalisierung = lokalisierung)

fun SpielMetaDaten.Companion.fromSkript(lokalisierung: Lokalisierung): SpielMetaDaten =
	SpielMetaDaten(SpielelementDaten(lokalisierung = lokalisierung))
