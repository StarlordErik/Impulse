package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

data class Kategorie(
	private val spielelementDaten: SpielelementDaten,

	private val sammlungDaten: SammlungDaten<Kartentext>,
): Spielelement by spielelementDaten, Sammlung<Kartentext> by sammlungDaten, Bestandteil {

	override fun getAktiveKartentexte(): Collection<Kartentext> =
		if (!inaktiv) bestandteile.flatMap { it.getAktiveKartentexte() } else emptyList()

	override fun getUnbesprocheneKartentexte(): Collection<Kartentext> =
		getAktiveKartentexte().flatMap { it.getUnbesprocheneKartentexte() }

	override fun getUngeseheneKartentexte(): Collection<Kartentext> =
		getUnbesprocheneKartentexte().flatMap { it.getUngeseheneKartentexte() }

	override fun setKartentexteUngesehen(): Collection<Kartentext> =
		getUnbesprocheneKartentexte().flatMap { it.setKartentexteUngesehen() }

	override fun setKartentexteUnbesprochen(): Collection<Kartentext> =
		getAktiveKartentexte().flatMap { it.setKartentexteUnbesprochen() }

	override fun getKategorieMitKarte(
		anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): Pair<Kategorie, List<Kartentext>> =
		this to bestandteile
			.flatMap { it.getUngeseheneKartentexte() }
			.chooseNkartentexte(anzahlTexte, bereitsEnthalteneKT)

	private fun Collection<Kartentext>.chooseNkartentexte(
		erforderlicheAnzahlAnKT: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): List<Kartentext> {
		if (erforderlicheAnzahlAnKT <= 0) return emptyList()

		val shuffled = this.shuffled()
		val erg = mutableListOf<Kartentext>()

		var i = 0
		var skip = 0
		while (i < erforderlicheAnzahlAnKT) {
			val index = i++ + skip
			if (index >= shuffled.size) return erg // keine OutOfBoundException pls

			val kt = shuffled[index]
			if (kt.id in bereitsEnthalteneKT.map { it.id }) {
				i--
				skip++
			} else {
				erg.add(kt)
			}
		}

		return erg + bereitsEnthalteneKT
	}
}
