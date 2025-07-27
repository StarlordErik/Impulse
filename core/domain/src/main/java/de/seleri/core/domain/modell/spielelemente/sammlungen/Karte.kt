package de.seleri.core.domain.modell.spielelemente.sammlungen

import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie

data class Karte(
	val kategorie: Kategorie, val kartentexte: List<Kartentext>
) {

	fun anzahlTexte(): Int =
		kartentexte.size
}
