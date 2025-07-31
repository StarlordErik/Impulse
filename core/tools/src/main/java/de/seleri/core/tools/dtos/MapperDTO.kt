package de.seleri.core.tools.dtos

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.mapper.toEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.tools.dtos.joins.KategorieXKartentextDTO
import de.seleri.core.tools.dtos.singles.LokalisierungDTO
import de.seleri.core.tools.dtos.singles.spielelemente.KartentextDTO
import de.seleri.core.tools.dtos.singles.spielelemente.KategorieDTO
import de.seleri.core.tools.dtos.singles.spielelemente.SpielDTO

fun Translation.toDTO(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationDTO =
	this.toEntity(lokalisierungID, sprache, ::TranslationDTO)

fun Lokalisierung.toDTO() =
	this.toEntity(::LokalisierungDTO)

fun Kartentext.toDTO() =
	this.toEntity(::KartentextDTO)

fun Kategorie.toDTO() =
	this.toEntity(::KategorieDTO)

fun Kategorie.toJoinDTO() =
	this.toEntity(::KategorieXKartentextDTO)

fun Spiel.toDTO() =
	this.toEntity(::SpielDTO)
