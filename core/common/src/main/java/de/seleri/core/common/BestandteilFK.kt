package de.seleri.core.common

sealed class BestandteilFK {

	abstract val bestandteilID: Int

	fun toInt(): Int =
		bestandteilID

	class LokalisierungID(override val bestandteilID: Int): BestandteilFK()

	class KartentextID(override val bestandteilID: Int): BestandteilFK()

	class KategorieID(override val bestandteilID: Int): BestandteilFK()
}
