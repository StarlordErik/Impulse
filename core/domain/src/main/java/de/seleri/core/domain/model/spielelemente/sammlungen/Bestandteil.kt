package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Spielelement

interface Bestandteil: Spielelement {

	fun isAktiv(): Boolean =
		!this.inaktiv
	fun getAktiveKartentexte(): Collection<Kartentext>
	fun getUnbesprocheneKartentexte(): Collection<Kartentext>
	fun getUngeseheneKartentexte(): Collection<Kartentext>

	fun setKartentexteUngesehen(): Collection<Kartentext>
	fun setKartentexteUnbesprochen(): Collection<Kartentext>
}
