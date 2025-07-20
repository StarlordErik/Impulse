package de.seleri.core.domain.model

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.sammlungen.Bestandteil

abstract class SpielelementDaten(
	val datenbankEintragsDaten: DatenbankEintragsDaten,

	val lokalisierungen: Collection<Bestandteil.LokalisierungID>,

	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
): DatenbankEintrag by datenbankEintragsDaten, Spielelement
