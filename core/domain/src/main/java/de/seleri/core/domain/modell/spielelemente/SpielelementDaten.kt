package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.EntityModell
import de.seleri.core.domain.modell.EntityModellDaten
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDaten(
	private val datenbankObjektDaten: EntityModellDaten = EntityModellDaten(),

	override val lokalisierungen: Collection<Lokalisierung>,

	override val ogSprache: Sprache = Konstanten.OG_SPRACHE,
	override val selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Konstanten.INAKTIV,
	override val favorisiert: Boolean = Konstanten.FAVORISIERT,
): EntityModell by datenbankObjektDaten, Spielelement {

	companion object {

		fun fromEingabe(
			lokalisierungen: Collection<Lokalisierung>,

			id: Int = Konstanten.ENTITY_ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT
		): SpielelementDaten =
			SpielelementDaten(
				datenbankObjektDaten = EntityModellDaten.fromEingabe(id),
				lokalisierungen = lokalisierungen,
				ogSprache = ogSprache,
				selbstErstellt = selbstErstellt,
				inaktiv = inaktiv,
				favorisiert = favorisiert
			)

		fun fromAllInOneEingabe(
			bezeichnung: String,

			id: Int = Konstanten.ENTITY_ID,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT
		): SpielelementDaten {
			val ogLokalisierung = Lokalisierung.fromEingabe(bezeichnung = bezeichnung)
			val lokalisierungen = mutableListOf(ogLokalisierung)

			if (ogSprache != ogLokalisierung.sprache) {
				lokalisierungen += Lokalisierung.fromEingabe(bezeichnung = bezeichnung, sprache = ogSprache)
			}

			return SpielelementDaten(
				datenbankObjektDaten = EntityModellDaten.fromEingabe(id), lokalisierungen = lokalisierungen.toList(),
				ogSprache = ogSprache,
				selbstErstellt = selbstErstellt,
				inaktiv = inaktiv,
				favorisiert = favorisiert
			)
		}
	}
}
