package de.seleri.core.tools.dtos.singles.lokStern.spielelemente

import de.seleri.core.common.entities.singles.lokStern.spielelemente.KategorieEntity
import de.seleri.core.common.id.LokalisierungID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KategorieDTO(

	@SerialName(value = "id")
	override val lokalisierungID: LokalisierungID,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,
): SpielelementDTO, BestandteilDTO, KategorieEntity

