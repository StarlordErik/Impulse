package de.seleri.core.domain.mapper

data class SpielEingabe(
	val translationen: Collection<SpracheMitBezeichnung>, val kategorieEingaben: Collection<KategorieEingabe>
)
