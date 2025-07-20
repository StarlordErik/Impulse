package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

data class SammlungDaten<ID: BestandteilID>(
	val bestandteile: Collection<ID>,
): Sammlung {

	override fun karte(texteProKarte: Int): List<BestandteilID.KartentextID> {
		TODO("Not yet implemented")
	}
}
