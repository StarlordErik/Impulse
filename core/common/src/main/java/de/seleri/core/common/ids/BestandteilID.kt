package de.seleri.core.common.ids

sealed class BestandteilID: idToInt {

	class LokalisierungID(override val idKey: Int): BestandteilID()

	class KartentextID(override val idKey: Int): BestandteilID()

	class KategorieID(override val idKey: Int): BestandteilID()
}
