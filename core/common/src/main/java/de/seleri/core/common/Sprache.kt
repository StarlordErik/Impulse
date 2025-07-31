package de.seleri.core.common

import de.seleri.core.common.konstanten.SpracheIDs


enum class Sprache(val id: Int) {

	OG(SpracheIDs.OG_ID),
	ERIK(SpracheIDs.ERIK_ID),
	DE(SpracheIDs.DE_ID),
	EN(SpracheIDs.EN_ID);


	companion object {

		fun fromId(id: Int): Sprache =
			entries.first { it.id == id }
	}
}
