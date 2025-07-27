package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.SpielIDint

data class SpielEingabe(
	val spielIDint: SpielIDint, val translationen: Collection<TranslationEingabe>,
	val kategorieEingaben: Collection<KategorieEingabe>
)
