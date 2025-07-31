package de.seleri.core.domain.modell

import de.seleri.core.common.konstanten.Default

data class Translation(
	val bezeichnung: String,
	val bearbeitet: Boolean = Default.BEARBEITET,
): ModellEntity {

	companion object
}
