package de.seleri.core.domain.model.spielelemente.spiel

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Konstanten
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.spielelemente.Spielelement
import de.seleri.core.domain.model.spielelemente.SpielelementDaten

class SpielMetaDaten(
	private val spielelementDaten: SpielelementDaten,

	override val bildDateiname: String? = Konstanten.BILD_DATEINAME,
): Spielelement by spielelementDaten, SpielMeta {

	companion object {

		fun fromEingabe(
			lokalisierungen: Collection<Lokalisierung>,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,
			bildDateiname: String? = Konstanten.BILD_DATEINAME
		): SpielMetaDaten =
			SpielMetaDaten(
				spielelementDaten = SpielelementDaten.fromEingabe(
					lokalisierungen = lokalisierungen,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert
				), bildDateiname = bildDateiname
			)

		fun fromAllInOneEingabe(
			name: String,

			id: Int = Konstanten.ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT,
			bildDateiname: String? = Konstanten.BILD_DATEINAME
		): SpielMetaDaten =
			SpielMetaDaten(
				spielelementDaten = SpielelementDaten.fromAllInOneEingabe(
					bezeichnung = name,

					id = id,
					ogSprache = ogSprache,
					selbstErstellt = selbstErstellt,
					inaktiv = inaktiv,
					favorisiert = favorisiert
				), bildDateiname = bildDateiname
			)
	}
}
