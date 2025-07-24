package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil

data class Kartentext(
	private val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean = false,
	val besprochen: Boolean = false,
): Spielelement by spielelementDaten, Bestandteil {

	override fun getAktiveKartentexte(): Collection<Kartentext> =
		if (!inaktiv) listOf(this) else emptyList()

	override fun getUnbesprocheneKartentexte(): Collection<Kartentext> =
		if (!besprochen) getAktiveKartentexte() else emptyList()

	override fun getUngeseheneKartentexte(): Collection<Kartentext> =
		if (!gesehen) getUnbesprocheneKartentexte() else emptyList()
}
