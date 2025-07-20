package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext

interface Sammlung<B: Bestandteil> {

	fun karte(texteProKarte: Int): List<Kartentext>
}
