package de.seleri.core.domain.modell.spielelemente.sammlungen

import de.seleri.core.common.ids.spielelementID.BestandteilID
import de.seleri.core.domain.modell.IDable
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface Bestandteil<BID: BestandteilID>: IDable<BID>, Spielelement {

	override val id: BID

	fun isAktiv(): Boolean =
		!this.inaktiv
	fun getAktiveKartentexte(): Collection<Kartentext>
	fun getUnbesprocheneKartentexte(): Collection<Kartentext>
	fun getUngeseheneKartentexte(): Collection<Kartentext>

	fun setKartentexteUngesehen(): Collection<Kartentext>
	fun setKartentexteUnbesprochen(): Collection<Kartentext>
}
