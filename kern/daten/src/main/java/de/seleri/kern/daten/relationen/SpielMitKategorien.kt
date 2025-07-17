package de.seleri.kern.daten.relationen

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.seleri.kern.daten.entities.Kategorie
import de.seleri.kern.daten.entities.Spiel
import de.seleri.kern.daten.entities.SpielXKategorie

data class SpielMitKategorien(
  @Embedded
  val spiel: Spiel,

  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      value = SpielXKategorie::class,
      parentColumn = "spielID",
      entityColumn = "kategorieID"
    )
  )

  val kategorien: List<Kategorie>,
)
