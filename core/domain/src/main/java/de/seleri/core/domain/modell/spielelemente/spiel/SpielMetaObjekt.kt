package de.seleri.core.domain.modell.spielelemente.spiel

data class SpielMetaObjekt(
	private val spielMetaDaten: SpielMetaDaten,
): SpielMeta by spielMetaDaten
