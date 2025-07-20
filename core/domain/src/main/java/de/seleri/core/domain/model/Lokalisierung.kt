package de.seleri.core.domain.model

import de.seleri.core.common.LokalisierungFK
import de.seleri.core.common.Sprache

data class Lokalisierung(
	val datenbankEintragsDaten: DatenbankEintragsDaten,

	val bezeichnung: String,
	val sprache: Sprache,
	val bearbeitet: Boolean,

	val spielelementFK: Pair<LokalisierungFK, Int>,
): DatenbankEintrag by datenbankEintragsDaten, Comparable<Lokalisierung> {

	override fun compareTo(other: Lokalisierung): Int {
		TODO("Not yet implemented")
	}
}
