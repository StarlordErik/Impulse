package de.seleri.core.tools.dtos.singles.lokStern.spielelemente

import de.seleri.core.common.entities.singles.lokStern.spielelemente.SpielEntity
import de.seleri.core.common.ids.LokalisierungID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpielDTO(

	@SerialName(value = "id")
	override val lokalisierungID: LokalisierungID,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	override val bildDateiname: String?,

	override val anleitung: String?,
	override val texteProKarte: Int,
): SpielelementDTO, SpielEntity
