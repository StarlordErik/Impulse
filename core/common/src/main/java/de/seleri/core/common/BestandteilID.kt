package de.seleri.core.common

sealed class BestandteilID {

	abstract val fk: Int

	fun toInt(): Int =
		fk

	class LokalisierungID(override val fk: Int): BestandteilID()

	class KartentextID(override val fk: Int): BestandteilID()

	class KategorieID(override val fk: Int): BestandteilID()
}
