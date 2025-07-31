package de.seleri.core.data

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.entities.TranslationRoom
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.domain.mapper.toEntity
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.modell.spielelemente.Kartentext

fun Translation.toRoom(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationRoom =
	toEntity(lokalisierungID, sprache, ::TranslationRoom)

fun Kartentext.toRoom() =
	this.toEntity(::KartentextRoom)
