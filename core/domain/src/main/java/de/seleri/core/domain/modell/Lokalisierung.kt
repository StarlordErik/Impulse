package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID

data class Lokalisierung(
	override val id: LokalisierungID,

	val ogSprache: Sprache,

	val translationen: Map<Sprache, Translation>
): ModellEntity {

	companion object
}
