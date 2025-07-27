package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.EntityModell
import de.seleri.core.domain.modell.Lokalisierung

interface Spielelement: EntityModell {

	val lokalisierungen: Collection<Lokalisierung>

	val ogSprache: Sprache
	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean

	fun getBezeichnung(sprache: Sprache): String =
		lokalisierungIn(sprache)?.bezeichnung
			?: if (sprache == ogSprache) lokalisierungIn(Sprache.OG)!!.bezeichnung
			else "localization unavailable"

	private fun lokalisierungIn(sprache: Sprache): Lokalisierung? =
		lokalisierungen.find { it.sprache == sprache }
}
