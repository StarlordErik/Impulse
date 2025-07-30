package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import kotlinx.serialization.Serializable

@Serializable
data class SpielXKategorieEntity(
	override val sammlungID: SpielID, override val bestandteilID: KategorieID
): JoinEntity<SpielID, KategorieID> {

	val spielID: SpielID get() = sammlungID
	val kategorieID: KategorieID get() = bestandteilID
}

