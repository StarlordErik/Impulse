package de.seleri.core.domain.model

import de.seleri.core.common.BestandteilFK
import de.seleri.core.common.Sprache

abstract class SpielelementDaten(
	val datenbankEintragsDaten: DatenbankEintragsDaten,

	val lokalisierungen: Collection<BestandteilFK.LokalisierungID>,

	val ogSprache: Sprache,
	val selbstErstellt: Boolean,
	val inaktiv: Boolean,
	val favorisiert: Boolean,
): DatenbankEintrag by datenbankEintragsDaten, Spielelement
