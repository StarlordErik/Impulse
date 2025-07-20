package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.BestandteilFK

interface Sammlung {

	fun karte(texteProKarte: Int): List<BestandteilFK.KartentextID>
}
