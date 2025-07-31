package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.modell.ModellEntity

data class SpielMetaObjekt(
	override val id: SpielID,
	private val spielMetaDaten: SpielMetaDaten,
): ModellEntity, SpielMeta by spielMetaDaten
