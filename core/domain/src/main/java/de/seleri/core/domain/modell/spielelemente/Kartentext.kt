package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.KartentextEingabe
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.spielelemente.sammlungen.Bestandteil

data class Kartentext(
	private val spielelementDaten: SpielelementDaten,

	val gesehen: Boolean = Konstanten.GESEHEN,
	val besprochen: Boolean = Konstanten.BESPROCHEN,
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

	companion object {

		fun forInitialdaten(ogSprache: Sprache, kartentextEingabe: KartentextEingabe): Kartentext =
			Kartentext(
				spielelementDaten = SpielelementDaten.forInitialdaten(ogSprache, kartentextEingabe.translationen)
			)
	}
}
