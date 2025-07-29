package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.SpielID
import kotlinx.serialization.Serializable

@Serializable
data class SpielEntity(
	override val id: SpielID,

	override val lokalisierungID: LokalisierungID,
	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val bildDateiname: String?,

	val anleitung: String?,
	val texteProKarte: Int,
): SpielelementEntity
