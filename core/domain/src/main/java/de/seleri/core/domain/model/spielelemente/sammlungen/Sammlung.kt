package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.ids.BestandteilID

interface Sammlung {

	fun karte(texteProKarte: Int): List<BestandteilID.KartentextID>
}
