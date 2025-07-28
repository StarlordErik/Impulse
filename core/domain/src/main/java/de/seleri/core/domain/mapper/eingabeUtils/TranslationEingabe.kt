package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Translation

fun Translation.Companion.fromSkript(freieTranslationID: Int, sprache: Sprache, bezeichnung: String): Translation? {
	return if (bezeichnung.isNotBlank()) Translation(TranslationIDint(freieTranslationID), sprache, bezeichnung)
	else null
}
