package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Konstanten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Karte
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung

data class Kategorie(
	private val spielelementDaten: SpielelementDaten,

	override val bestandteile: Collection<Kartentext>,
): Spielelement by spielelementDaten, Sammlung<Kartentext>, Bestandteil {

	override fun getAktiveKartentexte(): Collection<Kartentext> =
		if (!inaktiv) bestandteile.flatMap { it.getAktiveKartentexte() } else emptyList()

	override fun getUnbesprocheneKartentexte(): Collection<Kartentext> =
		getAktiveKartentexte().flatMap { it.getUnbesprocheneKartentexte() }

	override fun getUngeseheneKartentexte(): Collection<Kartentext> =
		getUnbesprocheneKartentexte().flatMap { it.getUngeseheneKartentexte() }

	override fun setKartentexteUngesehen(): Collection<Kartentext> =
		getUnbesprocheneKartentexte().flatMap { it.setKartentexteUngesehen() }

	override fun setKartentexteUnbesprochen(): Collection<Kartentext> =
		getAktiveKartentexte().flatMap { it.setKartentexteUnbesprochen() }

	override fun getKarte(
		anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): Karte =
		Karte(
			this,
			bestandteile
				.flatMap { it.getUngeseheneKartentexte() }
				.chooseNkartentexte(anzahlTexte, bereitsEnthalteneKT))

	private fun List<Kartentext>.chooseNkartentexte(
		erforderlicheAnzahlAnKT: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): List<Kartentext> {
		if (erforderlicheAnzahlAnKT <= 0) return emptyList()

		val shuffled = this.shuffled()
		val erg = mutableListOf<Kartentext>()

		var i = 0
		var skip = 0
		while (i < erforderlicheAnzahlAnKT) {
			val index = i++ + skip
			if (index >= shuffled.size) return erg // keine OutOfBoundException pls

			val kt = shuffled[index]
			if (kt.id in bereitsEnthalteneKT.map { it.id }) {
				i--
				skip++
			} else {
				erg.add(kt)
			}
		}

		return erg + bereitsEnthalteneKT
	}

	companion object {

		fun fromEingabe(
			lokalisierungen: Collection<Lokalisierung>,
			bestandteile: Collection<Kartentext>,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT
		): Kategorie =
			Kategorie(
				spielelementDaten = SpielelementDaten.fromEingabe(
					lokalisierungen = lokalisierungen,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert
				), bestandteile = bestandteile
			)

		fun fromAllInOneEingabe(
			name: String,
			kartentextTexte: Collection<String>,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,

			): Kategorie {
			val kartentexte = kartentextTexte.map {
				Kartentext.fromAllInOneEingabe(
					text = it, ogSprache = ogSprache, selbstErstellt = selbstErstellt, inaktiv = inaktiv
				)
			}

			return Kategorie(
				spielelementDaten = SpielelementDaten.fromAllInOneEingabe(
					bezeichnung = name,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert
				), bestandteile = kartentexte
			)
		}
	}
}
