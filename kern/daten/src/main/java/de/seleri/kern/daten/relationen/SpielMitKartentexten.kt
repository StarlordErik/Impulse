package de.seleri.kern.daten.relationen

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.seleri.kern.daten.entities.joins.SpielXKategorie
import de.seleri.kern.daten.entities.singles.KategorieEntity
import de.seleri.kern.daten.entities.singles.SpielEntity

data class SpielMitKartentexten(
  @Embedded
  val spiel: SpielEntity,

  @Relation(
    entity = KategorieEntity::class,
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      value = SpielXKategorie::class,
      parentColumn = "spielID",
      entityColumn = "kategorieID"
    )
  )
  val kategorienMitKartentexten: List<KategorieMitKartentexten>,
)
