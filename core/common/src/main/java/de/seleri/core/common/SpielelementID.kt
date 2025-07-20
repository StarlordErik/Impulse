package de.seleri.core.common

sealed class SpielelementID: idToInt {

	class KartentextID(override val idKey: Int): SpielelementID()

	class KategorieID(override val idKey: Int): SpielelementID()

	class SpielID(override val idKey: Int): SpielelementID()
}
