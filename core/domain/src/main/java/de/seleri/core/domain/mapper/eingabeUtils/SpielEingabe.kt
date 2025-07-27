package de.seleri.core.domain.mapper.eingabeUtils

data class SpielEingabe(
	val translationen: Collection<SpracheMitBezeichnung>, val kategorieEingaben: Collection<KategorieEingabe>
)
