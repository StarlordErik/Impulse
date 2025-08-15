package de.seleri.core.common

import de.seleri.core.common.konstanten.SpracheID
import kotlinx.serialization.Serializable

@Serializable
enum class Sprache(val id: Int) {

	OG(SpracheID.OG_ID),
	ERIK(SpracheID.ERIK_ID),
	DE(SpracheID.DE_ID),
	EN(SpracheID.EN_ID);


	companion object {

		fun fromId(id: Int): Sprache =
			entries.first { it.id == id }
	}
}
