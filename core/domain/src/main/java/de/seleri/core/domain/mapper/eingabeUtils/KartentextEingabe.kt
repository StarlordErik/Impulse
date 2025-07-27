package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.KartentextIDint

data class KartentextEingabe(
	val kartentextIDint: KartentextIDint,
	val translationen: Collection<SpracheMitBezeichnung>
)
