package de.seleri.core.domain.model.ids

sealed class BestandteilID: idToInt {

	class LokalisierungID(override val keyID: Int): BestandteilID()

	class KartentextID(override val keyID: Int): BestandteilID()

	class KategorieID(override val keyID: Int): BestandteilID()
}
