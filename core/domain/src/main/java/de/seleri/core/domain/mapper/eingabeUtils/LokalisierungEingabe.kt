package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.idInt.LokalisierungIDint

data class LokalisierungEingabe(
	val id: LokalisierungIDint, val translationen: Collection<TranslationEingabe>
)
