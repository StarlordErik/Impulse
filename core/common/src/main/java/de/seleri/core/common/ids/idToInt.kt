package de.seleri.core.common.ids

interface idToInt {

	val idKey: Int

	fun toInt(): Int =
		idKey
}
