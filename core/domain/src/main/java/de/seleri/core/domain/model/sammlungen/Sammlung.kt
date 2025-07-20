package de.seleri.core.domain.model.sammlungen

interface Sammlung {

	fun karte(texteProKarte: Int): List<Bestandteil.KartentextID>
}
