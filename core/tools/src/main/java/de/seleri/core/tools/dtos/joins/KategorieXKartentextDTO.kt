package de.seleri.core.tools.dtos.joins

import de.seleri.core.common.entities.joins.KategorieXKartentextEntity
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KategorieXKartentextDTO(

	@SerialName(value = "kategorieID")
	override val sammlungID: KategorieID,

	@SerialName(value = "kartentextID")
	override val bestandteilID: KartentextID,
): JoinDTO, KategorieXKartentextEntity


