package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Spielelement
import de.seleri.core.domain.model.SpielelementBasis
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil

data class Kategorie(
	val spielelementBasis: SpielelementBasis,
): Spielelement by spielelementBasis, Bestandteil {

	override fun wurdeGesehen() {
		TODO("Not yet implemented")
	}
}
