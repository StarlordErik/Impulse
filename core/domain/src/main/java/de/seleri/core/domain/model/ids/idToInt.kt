package de.seleri.core.domain.model.ids

interface idToInt {

	val keyID: Int

	fun toInt(): Int =
		keyID
}
