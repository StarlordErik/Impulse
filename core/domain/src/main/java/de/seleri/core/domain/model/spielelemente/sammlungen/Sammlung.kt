package de.seleri.core.domain.model.spielelemente.sammlungen

interface Sammlung {

	fun karte(texteProKarte: Int): List<Fremdschluessel.KartentextID>
}
