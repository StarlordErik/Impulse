package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.SpielEingabe
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.sammlungen.Karte
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung

data class Spiel(
	private val spielMetaDaten: SpielMetaDaten,

	val anleitung: String? = Konstanten.ANLEITUNG,
	val texteProKarte: Int = Konstanten.TEXTE_PRO_KARTE,

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

	companion object {

		fun forInitialdaten(ogSprache: Sprache, spielEingabe: SpielEingabe): Spiel {
			val kategorien = spielEingabe.kategorieEingaben.map { Kategorie.forInitialdaten(ogSprache, it) }

			return Spiel(
				spielMetaDaten = SpielMetaDaten.forInitialdaten(ogSprache, spielEingabe.translationen),
				bestandteile = kategorien
			)
		}
	}
}
