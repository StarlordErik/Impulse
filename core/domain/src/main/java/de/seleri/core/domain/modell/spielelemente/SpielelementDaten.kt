package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.TranslationEingabe
import de.seleri.core.domain.modell.Konstanten
import de.seleri.core.domain.modell.Lokalisierung

data class SpielelementDaten(
	override val lokalisierung: Lokalisierung,

	override val selbstErstellt: Boolean = Konstanten.SELBST_ERSTELLT,
	override val inaktiv: Boolean = Konstanten.INAKTIV,
	override val favorisiert: Boolean = Konstanten.FAVORISIERT,
): Spielelement {

	companion object {

		fun forInitialdaten(ogSprache: Sprache, translationen: Collection<TranslationEingabe>): SpielelementDaten =
			SpielelementDaten(lokalisierung = Lokalisierung.forInitialdaten(ogSprache, translationen))
	}
}
