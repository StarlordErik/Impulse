package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.domain.modell.EntityModell
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.sammlungen.Karte
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung

data class Spiel(
	override val id: SpielID,

	private val spielMetaDaten: SpielMetaDaten,

	val anleitung: String? = Konstanten.ANLEITUNG,
	val texteProKarte: Int = Konstanten.TEXTE_PRO_KARTE,

	override val bestandteile: Collection<Kategorie>,
): EntityModell, SpielMeta by spielMetaDaten, Sammlung<Kategorie> {

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
