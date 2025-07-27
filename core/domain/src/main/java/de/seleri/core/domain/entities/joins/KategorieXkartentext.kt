package de.seleri.core.domain.entities.joins

import de.seleri.core.common.idInt.KartentextIDint
import de.seleri.core.common.idInt.KategorieIDint


data class KategorieXkartentext(
	override val firstID: KategorieIDint, override val secondID: KartentextIDint
): JoinEntity(firstID, secondID)
