package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import kotlinx.serialization.Serializable

@Serializable
data class KategorieXkartentext(
	override val firstID: KategorieID, override val secondID: KartentextID
): JoinEntity
