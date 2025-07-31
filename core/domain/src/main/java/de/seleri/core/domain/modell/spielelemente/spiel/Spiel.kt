package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.konstanten.Default
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.sammlungen.Karte
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung

data class Spiel(
	private val spielMetaDaten: SpielMetaDaten,

	val anleitung: String? = Default.ANLEITUNG,
	val texteProKarte: Int = Default.TEXTE_PRO_KARTE,

	override val bestandteile: Collection<Kategorie>,
): SpielMeta by spielMetaDaten, Sammlung<Kategorie> {

	override fun getKarte(
		anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): Karte {
		return chooseKategorie().getKarte(texteProKarte)
	}

	private fun chooseKategorie(): Kategorie {
		return getAktiveBestandteile().random()
	}

	companion object
}
