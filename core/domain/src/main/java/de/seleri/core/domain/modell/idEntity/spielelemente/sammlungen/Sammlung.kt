package de.seleri.core.domain.modell.idEntity.spielelemente.sammlungen

import de.seleri.core.common.ids.spielelementID.SammlungID
import de.seleri.core.domain.modell.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.modell.idEntity.spielelemente.Spielelement

interface Sammlung<out B: Bestandteil<*>, SID: SammlungID>: Spielelement<SID> {

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
