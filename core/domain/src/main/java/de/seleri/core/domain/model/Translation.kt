package de.seleri.core.domain.model

import de.seleri.core.common.konstanten.Default

data class Translation(
	val bezeichnung: String,
	val bearbeitet: Boolean = Default.BEARBEITET,
): ModelEntity {

	companion object
}
