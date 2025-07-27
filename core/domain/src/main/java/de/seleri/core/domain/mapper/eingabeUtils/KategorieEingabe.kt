package de.seleri.core.domain.mapper.eingabeUtils

data class KategorieEingabe(
	val translationen: Collection<SpracheMitBezeichnung>, val kartentexte: Collection<KartentextEingabe>
)
