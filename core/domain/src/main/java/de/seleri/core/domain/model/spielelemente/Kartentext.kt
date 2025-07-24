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


	override fun setKartentexteUngesehen(): Collection<Kartentext> =
		if (gesehen && this in getUnbesprocheneKartentexte()) listOf(this.copy(gesehen = false)) else emptyList()

	override fun setKartentexteUnbesprochen(): Collection<Kartentext> =
		if (besprochen && this in getAktiveKartentexte()) setKartentexteUngesehen().map {
			it.copy(besprochen = false)
		} else emptyList()
}
