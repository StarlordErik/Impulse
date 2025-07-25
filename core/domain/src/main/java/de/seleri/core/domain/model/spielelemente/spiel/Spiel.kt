package de.seleri.core.domain.model.spielelemente.spiel

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung

data class Spiel(
	private val spielMetaDaten: SpielMetaDaten,

	val anleitung: String? = null,
	val texteProKarte: Int = 1,

	override val bestandteile: Collection<Kategorie>,
): SpielMeta by spielMetaDaten, Sammlung<Kategorie> {

	override fun getKategorieMitKarte(
		anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): Pair<Kategorie, List<Kartentext>> {
		return chooseKategorie().getKategorieMitKarte(texteProKarte)
	}

	private fun chooseKategorie(): Kategorie {
		return getAktiveBestandteile().random()
	}
}
