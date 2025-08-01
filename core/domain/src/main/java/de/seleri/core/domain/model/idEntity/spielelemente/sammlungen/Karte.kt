package de.seleri.core.domain.model.idEntity.spielelemente.sammlungen

import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie

data class Karte(
	val kategorie: Kategorie, val kartentexte: List<Kartentext>
) {

	fun anzahlTexte(): Int =
		kartentexte.size
}
