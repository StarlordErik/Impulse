package de.seleri.core.domain.model.ids

sealed class SammlungID: idToInt { class KategorieID(override val keyID: Int): SammlungID()
	class SpielID(override val keyID: Int): SammlungID()
}
