package de.seleri.core.domain.model.idTypes

sealed class SpielelementID: IDtoInt {

	class KartentextID(override val keyID: Int): SpielelementID()

	class KategorieID(override val keyID: Int): SpielelementID()

	class SpielID(override val keyID: Int): SpielelementID()
}
