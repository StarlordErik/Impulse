package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.SpielIDint

data class SpielEntity(
	override val id: SpielIDint,

	override val lokalisierungID: LokalisierungIDint,
	override val ogSprache: Sprache,
	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val anleitung: String?,
	val texteProKarte: Int,
	val bildDateiname: String?,
): SpielelementEntity
