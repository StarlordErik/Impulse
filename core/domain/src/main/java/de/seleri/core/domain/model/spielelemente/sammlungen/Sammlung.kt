package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext

interface Sammlung<B: Bestandteil> {

	fun erstelleKarte(texteProKarte: Int): List<Kartentext>
}
