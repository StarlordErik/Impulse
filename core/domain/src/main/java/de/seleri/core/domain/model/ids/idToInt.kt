package de.seleri.core.domain.model.ids

interface idToInt {

	val idKey: Int

	fun toInt(): Int =
		idKey
}
