package de.seleri.core.tools.dtos.singles.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.common.ids.LokalisierungID
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

