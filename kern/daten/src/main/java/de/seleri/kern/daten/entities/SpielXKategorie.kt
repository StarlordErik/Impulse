package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
  tableName = "SpielXKategorie",
  primaryKeys = ["spielId", "kategorieId"],
  foreignKeys = [ForeignKey(
    entity = Spiel::class,
    parentColumns = ["id"],
    childColumns = ["spielId"],
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = Kategorie::class,
    parentColumns = ["id"],
    childColumns = ["kategorieId"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class SpielXKategorie(
  val spielId: Int,
  val kategorieId: Int,
)

