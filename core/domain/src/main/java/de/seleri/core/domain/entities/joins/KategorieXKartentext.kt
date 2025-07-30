package de.seleri.core.domain.entities.joins

import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import kotlinx.serialization.Serializable

@Serializable
data class KategorieXKartentext(
	override val sammlungID: KategorieID, override val bestandteilID: KartentextID
): Join<KategorieID, KartentextID> {

	val kategorieID: KategorieID get() = sammlungID
	val kartentextID: KartentextID get() = bestandteilID
}
