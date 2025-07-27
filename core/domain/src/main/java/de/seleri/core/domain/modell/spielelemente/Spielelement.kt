package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.Lokalisierung

interface Spielelement {

	val lokalisierung: Lokalisierung

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean

	fun getBezeichnung(sprache: Sprache): String =
		lokalisierung.translationen.find { it.sprache == sprache }?.bezeichnung
			?: "localization unavailable"
}
