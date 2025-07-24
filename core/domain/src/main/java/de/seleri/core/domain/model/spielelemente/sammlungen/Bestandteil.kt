package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext

interface Bestandteil {

	fun getAktiveKartentexte(): Collection<Kartentext>
	fun getUnbesprocheneKartentexte(): Collection<Kartentext>
	fun getUngeseheneKartentexte(): Collection<Kartentext>
}
