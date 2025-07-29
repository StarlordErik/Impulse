package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.domain.modell.EntityModell

data class SpielMetaObjekt(
	override val id: SpielID,
	private val spielMetaDaten: SpielMetaDaten,
): EntityModell, SpielMeta by spielMetaDaten
