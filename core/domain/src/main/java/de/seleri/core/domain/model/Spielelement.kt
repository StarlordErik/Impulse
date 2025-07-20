package de.seleri.core.domain.model

interface Spielelement: DatenbankEintrag, Comparable<Spielelement> {
	val lokalisierungen: Collection<Lokalisierung>

	override fun compareTo(other: Spielelement): Int {
		TODO("Not yet implemented")
	}
}
