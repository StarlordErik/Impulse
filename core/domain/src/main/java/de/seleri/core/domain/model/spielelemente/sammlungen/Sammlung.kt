package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.BestandteilID

interface Sammlung {

	fun karte(texteProKarte: Int): List<BestandteilID.KartentextID>
}
