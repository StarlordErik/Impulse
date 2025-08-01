package de.seleri.core.domain.model.idEntity.spielelemente.spiel

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.common.konstanten.Default
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.model.idEntity.spielelemente.sammlungen.Karte
import de.seleri.core.domain.model.idEntity.spielelemente.sammlungen.Sammlung

data class Spiel(
	private val spielMetaDaten: SpielMetaDO,

	val anleitung: String? = Default.ANLEITUNG,
	val texteProKarte: Int = Default.TEXTE_PRO_KARTE,

	override val bestandteile: Collection<Kategorie>,
): SpielMeta by spielMetaDaten, Sammlung<Kategorie, SpielID> {

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
