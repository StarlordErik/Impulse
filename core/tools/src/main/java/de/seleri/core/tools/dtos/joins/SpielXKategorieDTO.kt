package de.seleri.core.tools.dtos.joins

import de.seleri.core.common.entities.joins.SpielXKategorieEntity
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpielXKategorieDTO(

	@SerialName(value = "spielID")
	override val sammlungID: SpielID,

	@SerialName(value = "kategorieID")
	override val bestandteilID: KategorieID,
): JoinDTO, SpielXKategorieEntity
