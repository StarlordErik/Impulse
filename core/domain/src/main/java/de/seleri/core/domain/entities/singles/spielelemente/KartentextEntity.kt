package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.KartentextIDint
import de.seleri.core.common.idInt.LokalisierungIDint

data class KartentextEntity(
	override val id: KartentextIDint,

	override val lokalisierungID: LokalisierungIDint,
	override val ogSprache: Sprache,
	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val gesehen: Boolean,
	val besprochen: Boolean,
): SpielelementEntity
