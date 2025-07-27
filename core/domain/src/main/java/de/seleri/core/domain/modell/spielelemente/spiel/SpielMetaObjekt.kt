package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.idInt.SpielIDint
import de.seleri.core.domain.modell.EntityModell

data class SpielMetaObjekt(
	override val id: SpielIDint,
	private val spielMetaDaten: SpielMetaDaten,
): EntityModell, SpielMeta by spielMetaDaten
