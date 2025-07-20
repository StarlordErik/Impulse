package de.seleri.core.domain.model.ids

sealed class SpielelementID: idToInt {

	class KartentextID(override val keyID: Int): SpielelementID()

	class KategorieID(override val keyID: Int): SpielelementID()

	class SpielID(override val keyID: Int): SpielelementID()
}
