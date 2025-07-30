package de.seleri.core.tools.dtos.joins

import de.seleri.core.common.entities.joins.SpielXKategorieEntity
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import kotlinx.serialization.Serializable

@Serializable
data class SpielXKategorieDTO(
	override val sammlungID: SpielID,

	override val bestandteilID: KategorieID,
): JoinDTO, SpielXKategorieEntity
