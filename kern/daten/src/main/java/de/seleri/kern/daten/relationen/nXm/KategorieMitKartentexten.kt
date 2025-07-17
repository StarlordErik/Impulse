package de.seleri.kern.daten.relationen.nXm

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.seleri.kern.daten.entities.Kartentext
import de.seleri.kern.daten.entities.Kategorie
import de.seleri.kern.daten.entities.KategorieXKartentexte

data class KategorieMitKartentexten(
  @Embedded
  val kategorie: Kategorie,
  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      value = KategorieXKartentexte::class,
      parentColumn = "kategorieID",
      entityColumn = "kartentextID"
    )
  )
  val kartentexte: List<Kartentext>,
)
