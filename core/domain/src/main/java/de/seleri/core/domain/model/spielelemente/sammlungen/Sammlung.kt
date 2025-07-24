package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext

interface Sammlung<B: Bestandteil> {

	val bestandteile: Collection<B>

	fun setAllKTungesehen(): Collection<Kartentext> =
		bestandteile.flatMap { it.setKartentexteUngesehen() }

	fun setAllKTunbesprochen(): Collection<Kartentext> =
		bestandteile.flatMap { it.setKartentexteUnbesprochen() }

	fun getKarte(anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext> = emptyList()): List<Kartentext> =
		bestandteile
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
