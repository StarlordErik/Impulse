package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.ModellEntity

interface SpielelementDaten: ModellEntity {

	val lokalisierung: Lokalisierung

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean

	fun getBezeichnung(sprache: Sprache): String =
		lokalisierung.translationen[sprache]?.bezeichnung
			?: "localization unavailable"

}
