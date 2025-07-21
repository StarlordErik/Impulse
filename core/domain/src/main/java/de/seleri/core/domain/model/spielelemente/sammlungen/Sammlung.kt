package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

interface Sammlung {

	fun karte(texteProKarte: Int): Collection<BestandteilID.KartentextID>
}
