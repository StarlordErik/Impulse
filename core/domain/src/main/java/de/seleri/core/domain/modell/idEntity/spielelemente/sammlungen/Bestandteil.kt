package de.seleri.core.domain.modell.idEntity.spielelemente.sammlungen

import de.seleri.core.common.ids.spielelementID.BestandteilID
import de.seleri.core.domain.modell.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.modell.idEntity.spielelemente.Spielelement

interface Bestandteil<BID: BestandteilID>: Spielelement<BID> {

	override val id: BID

	fun isAktiv(): Boolean =
		!this.inaktiv
	fun getAktiveKartentexte(): Collection<Kartentext>
	fun getUnbesprocheneKartentexte(): Collection<Kartentext>
	fun getUngeseheneKartentexte(): Collection<Kartentext>

	fun setKartentexteUngesehen(): Collection<Kartentext>
	fun setKartentexteUnbesprochen(): Collection<Kartentext>
}
