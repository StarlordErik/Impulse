package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.joins.SpielXkategorie
import de.seleri.core.domain.entities.singles.spielelemente.SpielEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDaten

fun SpielEntity.toModell(lokalisierung: Lokalisierung, kategorien: Collection<Kategorie>): Spiel =
	Spiel(
		id = this.id, spielMetaDaten = SpielMetaDaten(
			spielelementDaten = SpielelementDaten(
				lokalisierung = lokalisierung,
				selbstErstellt = this.selbstErstellt,
				inaktiv = this.inaktiv,
				favorisiert = this.favorisiert
			), bildDateiname = this.bildDateiname
		), anleitung = this.anleitung, texteProKarte = this.texteProKarte, bestandteile = kategorien
	)

fun Spiel.toEntity(): SpielEntity =
	SpielEntity(
		id = this.id,
		lokalisierungID = this.lokalisierung.id,
		selbstErstellt = this.selbstErstellt,
		inaktiv = this.inaktiv,
		favorisiert = this.favorisiert,
		anleitung = this.anleitung,
		texteProKarte = this.texteProKarte,
		bildDateiname = this.bildDateiname
	)

fun Spiel.toDatenbankSlice(): DatenbankSlice {
	val lokalisierungSlice = this.lokalisierung.toDatenbankSlice()

	val kategorieSlices = this.bestandteile.map { it.toDatenbankSlice() }

	val spielXkategorie = this.bestandteile.map { SpielXkategorie(this.id, it.id) }
	val spielXkategorieSlice = DatenbankSlice(spielXkategorien = spielXkategorie.toMutableList())

	return DatenbankSlice.merged(
		others = kategorieSlices + lokalisierungSlice + spielXkategorieSlice, spiele = mutableListOf(this.toEntity())
	)
}
