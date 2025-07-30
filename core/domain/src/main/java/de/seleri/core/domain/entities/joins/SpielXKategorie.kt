package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import kotlinx.serialization.Serializable

@Serializable
data class SpielXKategorie(
	override val sammlungID: SpielID, override val bestandteilID: KategorieID
): Join<SpielID, KategorieID>
