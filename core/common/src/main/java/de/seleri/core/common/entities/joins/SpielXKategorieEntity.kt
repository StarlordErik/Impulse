package de.seleri.core.common.entities.joins

import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID

interface SpielXKategorieEntity: JoinEntity<SpielID, KategorieID> {

	override val sammlungID: SpielID
	override val bestandteilID: KategorieID

	val spielID: SpielID get() = sammlungID
	val kategorieID: KategorieID get() = bestandteilID
}

