package de.seleri.core.domain.model.idEntity

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation

data class Lokalisierung(
	override val id: LokalisierungID,

	val ogSprache: Sprache,

	val translationen: Map<Sprache, Translation>
): IDentity<LokalisierungID> {

	companion object
}
