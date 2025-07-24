package de.seleri.core.domain.model

interface DatenbankObjekt: Comparable<DatenbankObjekt> {

	val id: Int

	// TODO muss auch über Entity-Namen gehen! (SpielMetaObjekt == Spiel)
	override fun compareTo(other: DatenbankObjekt): Int =
		this.id.compareTo(other.id)
}
