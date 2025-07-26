package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.DatenbankObjektDaten
import de.seleri.core.domain.model.Konstanten
import de.seleri.core.domain.model.Lokalisierung

data class SpielelementDaten(
	private val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	override val lokalisierungen: Collection<Lokalisierung>,

	override val ogSprache: Sprache = Konstanten.OG_SPRACHE,
	override val selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Konstanten.INAKTIV,
	override val favorisiert: Boolean = Konstanten.FAVORISIERT,
): DatenbankObjekt by datenbankObjektDaten, Spielelement {

	companion object {

		fun fromEingabe(
			id: Int = Konstanten.ID,
			lokalisierungen: Collection<Lokalisierung>,
			ogSprache: Sprache = Konstanten.OG_SPRACHE,
			selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
			inaktiv: Boolean = Konstanten.INAKTIV,
			favorisiert: Boolean = Konstanten.FAVORISIERT
		): SpielelementDaten =
			SpielelementDaten(
				datenbankObjektDaten = DatenbankObjektDaten.fromEingabe(id),
				lokalisierungen = lokalisierungen,
				ogSprache = ogSprache,
				selbstErstellt = selbstErstellt,
				inaktiv = inaktiv,
				favorisiert = favorisiert
			)
	}
}
