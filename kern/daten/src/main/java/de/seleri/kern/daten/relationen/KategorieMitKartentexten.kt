package de.seleri.kern.daten.relationen

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.seleri.kern.daten.entities.joins.KategorieXKartentext
import de.seleri.kern.daten.entities.singles.Kartentext
import de.seleri.kern.daten.entities.singles.Kategorie

data class KategorieMitKartentexten(
  @Embedded
  val kategorie: Kategorie,

  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      value = KategorieXKartentext::class,
      parentColumn = "kategorieID",
      entityColumn = "kartentextID"
    )
  )

  val kartentexte: List<Kartentext>,
)
