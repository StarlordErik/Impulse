package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Konstanten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil

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

		fun fromEingabe(
			lokalisierungen: Collection<Lokalisierung>,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,
			gesehen: Boolean = Konstanten.GESEHEN,
			besprochen: Boolean = Konstanten.BESPROCHEN
		): Kartentext =
			Kartentext(
				spielelementDaten = SpielelementDaten.fromEingabe(
					lokalisierungen = lokalisierungen,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert
				), gesehen = gesehen, besprochen = besprochen
			)

		fun fromAllInOneEingabe(
			bezeichnung: String,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,
			gesehen: Boolean = Konstanten.GESEHEN,
			besprochen: Boolean = Konstanten.BESPROCHEN
		): Kartentext =
			Kartentext(
				spielelementDaten = SpielelementDaten.fromAllInOneEingabe(
					bezeichnung = bezeichnung,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert
				), gesehen = gesehen, besprochen = besprochen
			)
	}
}
