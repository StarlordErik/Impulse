package de.seleri.core.tools.dtos

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.mapper.toEntity
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.tools.dtos.singles.spielelemente.KartentextDTO

fun Translation.toDTO(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationDTO =
	toEntity(lokalisierungID, sprache, ::TranslationDTO)

fun Kartentext.toDTO() =
	this.toEntity(::KartentextDTO)
