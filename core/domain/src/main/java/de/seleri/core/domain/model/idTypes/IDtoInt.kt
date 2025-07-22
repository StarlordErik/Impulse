package de.seleri.core.domain.model.idTypes

interface IDtoInt {

	val keyID: Int

	fun toInt(): Int =
		keyID
}
