package de.seleri.core.domain.entities.joins

import de.seleri.core.common.idInt.KategorieIDint
import de.seleri.core.common.idInt.SpielIDint


data class SpielXkategorie(
	override val firstID: SpielIDint, override val secondID: KategorieIDint
): JoinEntity(firstID, secondID)
