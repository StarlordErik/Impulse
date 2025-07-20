package de.seleri.core.domain.model

import de.seleri.core.common.Sprache

data class Lokalisierung(
	val datenbankEintrag: DatenbankEintrag,

	val bezeichnung: String,
	val sprache: Sprache,
	val bearbeitet: Boolean,

	val spielelementID: Int,
): DatenbankEintrag by datenbankEintrag, Comparable<Lokalisierung> {

	override fun compareTo(other: Lokalisierung): Int {
		TODO("Not yet implemented")
	}
}
