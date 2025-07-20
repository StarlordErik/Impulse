package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementBasis

data class Spiel(
	val spielelementBasis: SpielelementBasis,

	val texteProKarte: Int,
	val bildDateiname: String?,
): Spielelement by spielelementBasis {

}
