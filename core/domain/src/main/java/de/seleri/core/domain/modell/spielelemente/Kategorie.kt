package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.KategorieIDint
import de.seleri.core.domain.mapper.eingabeUtils.KategorieEingabe
import de.seleri.core.domain.modell.EntityModell
import de.seleri.core.domain.modell.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.modell.spielelemente.sammlungen.Karte
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung

data class Kategorie(
	override val id: KategorieIDint,

	private val spielelementDaten: SpielelementDaten,

	override val bestandteile: Collection<Kartentext>,
): EntityModell, Spielelement by spielelementDaten, Sammlung<Kartentext>, Bestandteil {

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

		fun forInitialdaten(
			ogSprache: Sprache,
			kategorieEingabe: KategorieEingabe,
		): Kategorie {
			val kartentexte = kategorieEingabe.kartentexte.map { Kartentext.forInitialdaten(ogSprache, it) }

			return Kategorie(
				spielelementDaten = SpielelementDaten.forInitialdaten(ogSprache, kategorieEingabe.translationen),
				bestandteile = kartentexte
			)
		}
	}
}
