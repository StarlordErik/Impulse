package de.seleri.kern.daten.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import de.seleri.kern.daten.entities.Kartentext
import de.seleri.kern.daten.entities.Kategorie

@Entity(
  tableName = "KategorieXKartentext",
  primaryKeys = ["kategorieID", "kartentextID"],
  foreignKeys = [ForeignKey(
    entity = Kategorie::class,
    parentColumns = ["id"],
    childColumns = ["kategorieID"],
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = Kartentext::class,
    parentColumns = ["id"],
    childColumns = ["kartentextID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class KategorieXKartentext(
  val kategorieID: Int,
  val kartentextID: Int,
)
