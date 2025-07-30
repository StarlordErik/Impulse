package de.seleri.core.data.entities.joins

import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.SammlungID
import de.seleri.core.data.entities.EntityRoom

interface JoinRoom: EntityRoom {

	val firstID: Int
	val secondID: Int

	val sammlungID: SammlungID

	val bestandteilID: BestandteilID
}
