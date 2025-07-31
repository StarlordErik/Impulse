package de.seleri.core.domain.mapper.spiel

import de.seleri.core.common.entities.singles.spielelemente.SpielEntity
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel

// @formatter:off
fun SpielEntity.toDomain(lokalisierung: Lokalisierung, kategorien: Collection<Kategorie>) : Spiel =
	Spiel(
		spielMetaDaten = SpielMetaMapper(this, lokalisierung),
		anleitung = this.anleitung,
		texteProKarte = this.texteProKarte,
		bestandteile = kategorien
	)

typealias SpielFactory<S> = (
	lokalisierungID: LokalisierungID,
	selbstErstellt: Boolean,
	inaktiv: Boolean,
	favorisiert: Boolean,
	bildDateiname: String?,
	anleitung: String?,
	texteProKarte: Int
) -> S

fun <E: SpielEntity> Spiel.toEntity(factory: SpielFactory<E>): E =
	factory(
		this.lokalisierung.id,
		this.selbstErstellt,
		this.inaktiv,
		this.favorisiert,
		this.bildDateiname,
		this.anleitung,
		this.texteProKarte
	)
// @formatter:on

