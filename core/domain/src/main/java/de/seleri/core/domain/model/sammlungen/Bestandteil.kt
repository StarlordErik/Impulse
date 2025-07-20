package de.seleri.core.domain.model.sammlungen


sealed class Bestandteil {

	abstract val bestandteilID: Int

	fun toInt(): Int =
		bestandteilID

	class LokalisierungID(override val bestandteilID: Int): Bestandteil()

	class KartentextID(override val bestandteilID: Int): Bestandteil()

	class KategorieID(override val bestandteilID: Int): Bestandteil()
}


