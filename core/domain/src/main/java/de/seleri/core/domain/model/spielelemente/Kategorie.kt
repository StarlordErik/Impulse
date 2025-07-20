package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementBasis

data class Kategorie(
	val spielelementBasis: SpielelementBasis,
): Spielelement by spielelementBasis {

}
