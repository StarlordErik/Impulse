package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.sammlungen.Karte
import de.seleri.core.domain.modell.spielelemente.sammlungen.Sammlung

data class Spiel(
	private val spielMetaDaten: SpielMetaDaten,

	val anleitung: String? = Konstanten.ANLEITUNG,
	val texteProKarte: Int = Konstanten.TEXTE_PRO_KARTE,

	override val bestandteile: Collection<Kategorie>,
): SpielMeta by spielMetaDaten, Sammlung<Kategorie> {

	override fun getKarte(
		anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): Karte {
		return chooseKategorie().getKarte(texteProKarte)
	}

	private fun chooseKategorie(): Kategorie {
		return getAktiveBestandteile().random()
	}

	companion object {

		fun fromEingabe(
			lokalisierungen: Collection<Lokalisierung>,
			bestandteile: Collection<Kategorie>,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,
			bildDateiname: String? = Konstanten.BILD_DATEINAME,
			anleitung: String? = Konstanten.ANLEITUNG,
			texteProKarte: Int = Konstanten.TEXTE_PRO_KARTE
		): Spiel =
			Spiel(
				spielMetaDaten = SpielMetaDaten.fromEingabe(
					lokalisierungen = lokalisierungen,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert,
					bildDateiname = bildDateiname
				), bestandteile = bestandteile, anleitung = anleitung, texteProKarte = texteProKarte
			)

		fun fromAllInOneEingabe(
			name: String,
			kategorienMitKartentexten: Collection<Pair<String, Collection<String>>>,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,
			bildDateiname: String? = Konstanten.BILD_DATEINAME,
			anleitung: String? = Konstanten.ANLEITUNG,
			texteProKarte: Int = Konstanten.TEXTE_PRO_KARTE
		): Spiel {
			val kategorien = kategorienMitKartentexten.map {
				Kategorie.fromAllInOneEingabe(
					name = it.first,
					kartentextTexte = it.second,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv
				)
			}

			return Spiel(
				spielMetaDaten = SpielMetaDaten.fromAllInOneEingabe(
					name = name,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert,
					bildDateiname = bildDateiname
				), anleitung = anleitung, texteProKarte = texteProKarte, bestandteile = kategorien
			)
		}
	}
}
