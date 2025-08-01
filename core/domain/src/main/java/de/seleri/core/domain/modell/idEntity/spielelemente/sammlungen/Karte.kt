package de.seleri.core.domain.modell.idEntity.spielelemente.sammlungen

import de.seleri.core.domain.modell.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.modell.idEntity.spielelemente.Kategorie

data class Karte(
	val kategorie: Kategorie, val kartentexte: List<Kartentext>
) {

	fun anzahlTexte(): Int =
		kartentexte.size
}
