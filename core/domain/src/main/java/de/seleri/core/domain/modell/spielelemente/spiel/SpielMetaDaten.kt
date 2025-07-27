package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.SpracheMitBezeichnung
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.spielelemente.Spielelement
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

class SpielMetaDaten(
	private val spielelementDaten: SpielelementDaten,

	override val bildDateiname: String? = Konstanten.BILD_DATEINAME,
): Spielelement by spielelementDaten, SpielMeta {

	companion object {

		fun forInitialdaten(ogSprache: Sprache, spielTranslationen: Collection<SpracheMitBezeichnung>): SpielMetaDaten =
			SpielMetaDaten(
				spielelementDaten = SpielelementDaten.forInitialdaten(ogSprache, spielTranslationen),
			)
	}
}
