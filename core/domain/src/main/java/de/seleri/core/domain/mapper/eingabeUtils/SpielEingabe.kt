package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.SpielIDint

data class SpielEingabe(
	val spielIDint: SpielIDint,
	val translationen: Collection<SpracheMitBezeichnung>,
	val kategorieEingaben: Collection<KategorieEingabe>
)
