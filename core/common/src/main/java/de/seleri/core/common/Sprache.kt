package de.seleri.core.common


enum class Sprache(val id: Int) { OG(OG_ID),
	ERIK(ERIK_ID),
	DE(DE_ID),
	EN(EN_ID);


	companion object {

		fun fromId(id: Int): Sprache =
			entries.first { it.id == id }
	}
}
