package de.seleri.core.domain.model

interface DatenbankEintrag {
	val id: Int

	fun compareByIdTo(other: DatenbankEintrag): Int {
		TODO("Not yet implemented")
	}
}
