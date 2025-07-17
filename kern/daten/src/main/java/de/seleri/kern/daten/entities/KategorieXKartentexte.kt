package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
  tableName = "KategorieXKartentexte",
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
data class KategorieXKartentexte(
  val kategorieID: Int,
  val kartentextID: Int,
)
