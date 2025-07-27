package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.KategorieIDint

data class KategorieEingabe(
	val kategorieIDint: KategorieIDint,
	val translationen: Collection<SpracheMitBezeichnung>,
	val kartentexte: Collection<KartentextEingabe>
)
