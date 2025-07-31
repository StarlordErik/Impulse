package de.seleri.core.domain.modell.spielelemente.sammlungen

import de.seleri.core.common.ids.spielelementID.SammlungID
import de.seleri.core.domain.modell.IDable
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface Sammlung<out B: Bestandteil<*>, SID: SammlungID>: IDable<SID>, Spielelement {

	override val id: SID

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
