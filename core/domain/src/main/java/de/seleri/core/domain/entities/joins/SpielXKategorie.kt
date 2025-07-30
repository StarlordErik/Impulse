package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import kotlinx.serialization.Serializable

@Serializable
data class SpielXKategorie(
	override val firstID: SpielID, override val secondID: KategorieID
): Join
