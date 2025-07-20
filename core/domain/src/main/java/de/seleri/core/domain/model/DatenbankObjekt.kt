package de.seleri.core.domain.model

interface DatenbankObjekt {
	val id: Int

	fun compareByIdTo(other: DatenbankObjekt): Int {
		TODO("Not yet implemented")
	}
}
