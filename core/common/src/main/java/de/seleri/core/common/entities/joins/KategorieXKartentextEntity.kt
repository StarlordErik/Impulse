package de.seleri.core.common.entities.joins

import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID

interface KategorieXKartentextEntity: JoinEntity<KategorieID, KartentextID> {

	override val sammlungID: KategorieID
	override val bestandteilID: KartentextID

	val kategorieID: KategorieID get() = sammlungID
	val kartentextID: KartentextID get() = bestandteilID
}
