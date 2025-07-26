package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie

data class Karte(
	val kategorie: Kategorie, val kartentexte: List<Kartentext>
) {

	fun anzahlTexte(): Int =
		kartentexte.size
}
