package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
  tableName = "SpielXKategorie",
  primaryKeys = ["spielID", "kategorieID"],
  foreignKeys = [ForeignKey(
    entity = Spiel::class,
    parentColumns = ["id"],
    childColumns = ["spielID"],
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = Kategorie::class,
    parentColumns = ["id"],
    childColumns = ["kategorieID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class SpielXKategorie(
  val spielID: Int,
  val kategorieID: Int,
)
