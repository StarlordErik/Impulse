package de.seleri.core.domain.mapper

data class KategorieEingabe(
	val translationen: Collection<SpracheMitBezeichnung>, val kartentexte: Collection<KartentextEingabe>
)
