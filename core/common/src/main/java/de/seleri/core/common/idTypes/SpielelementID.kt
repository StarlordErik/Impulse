package de.seleri.core.common.idTypes

sealed class SpielelementID(override val keyID: Int): IDtoInt {

	class KartentextID(keyID: Int): SpielelementID(keyID)

	class KategorieID(keyID: Int): SpielelementID(keyID)

	class SpielID(keyID: Int): SpielelementID(keyID)
}
