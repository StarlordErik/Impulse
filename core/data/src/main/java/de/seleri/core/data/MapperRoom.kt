package de.seleri.core.data

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.entities.TranslationRoom
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom
import de.seleri.core.domain.mapper.toEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel

fun Translation.toRoom(lokalisierungID: LokalisierungID, sprache: Sprache): TranslationRoom =
	this.toEntity(lokalisierungID, sprache, ::TranslationRoom)

fun Lokalisierung.toRoom() =
	this.toEntity(::LokalisierungRoom)

fun Kartentext.toRoom() =
	this.toEntity(::KartentextRoom)

fun Kategorie.toRoom() =
	this.toEntity(::KategorieRoom)

fun Kategorie.toJoinRoom() =
	this.toEntity(::KategorieXKartentextRoom)

fun Spiel.toRoom() =
	this.toEntity(::SpielRoom)
