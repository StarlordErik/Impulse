package de.seleri.core.common.idTypes

interface IDtoInt {

	val keyID: Int

	fun toInt(): Int =
		keyID
}
