package de.seleri.kern.daten.relationen

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.seleri.kern.daten.entities.joins.KategorieXKartentext
import de.seleri.kern.daten.entities.singles.KartentextEntity
import de.seleri.kern.daten.entities.singles.KategorieEntity

data class KategorieMitKartentexten(
  @Embedded
  val kategorie: KategorieEntity,

  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    associateBy = Junction(
      value = KategorieXKartentext::class,
      parentColumn = "kategorieID",
      entityColumn = "kartentextID"
    )
  )

  val kartentexte: List<KartentextEntity>,
)
