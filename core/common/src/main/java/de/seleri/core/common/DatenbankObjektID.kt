package de.seleri.core.common

sealed class DatenbankObjektID {

	abstract val objektID: Int

	fun toInt(): Int =
		objektID

	class LokalisierungID(override val objektID: Int): DatenbankObjektID()

	class KartentextID(override val objektID: Int): DatenbankObjektID()

	class KategorieID(override val objektID: Int): DatenbankObjektID()

	class SpielID(override val objektID: Int): DatenbankObjektID()
}
