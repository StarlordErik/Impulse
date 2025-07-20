package de.seleri.core.common

sealed class SpielelementID {

	abstract val id: Int

	fun toInt(): Int =
		id

	class KartentextID(override val id: Int): SpielelementID()

	class KategorieID(override val id: Int): SpielelementID()

	class SpielID(override val id: Int): SpielelementID()
}
