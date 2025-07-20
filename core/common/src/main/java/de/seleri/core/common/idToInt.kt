package de.seleri.core.common

interface idToInt {

	val idKey: Int

	fun toInt(): Int =
		idKey
}
