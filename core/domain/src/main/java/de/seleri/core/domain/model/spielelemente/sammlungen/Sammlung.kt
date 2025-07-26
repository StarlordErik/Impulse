package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext

interface Sammlung<out B: Bestandteil> {

	val bestandteile: Collection<B>

	fun getAktiveBestandteile(): Collection<B> =
		bestandteile.filter { it.isAktiv() }

	fun setAllKTungesehen(): Collection<Kartentext> =
		bestandteile.flatMap { it.setKartentexteUngesehen() }

	fun setAllKTunbesprochen(): Collection<Kartentext> =
		bestandteile.flatMap { it.setKartentexteUnbesprochen() }

	fun getKarte(
		anzahlTexte: Int,
		bereitsEnthalteneKT: Collection<Kartentext> = emptyList()
	): Karte

}
