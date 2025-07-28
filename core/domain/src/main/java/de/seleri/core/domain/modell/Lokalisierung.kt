package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint

data class Lokalisierung(
	override val id: LokalisierungIDint,

	val ogSprache: Sprache, val translationen: Collection<Translation>
): EntityModell {

	companion object
}
