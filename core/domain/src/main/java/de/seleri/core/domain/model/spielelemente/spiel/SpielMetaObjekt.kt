package de.seleri.core.domain.model.spielelemente.spiel

data class SpielMetaObjekt(
	private val spielMetaDaten: SpielMetaDaten,
): SpielMeta by spielMetaDaten
