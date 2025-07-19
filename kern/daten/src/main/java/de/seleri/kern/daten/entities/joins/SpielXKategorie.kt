package de.seleri.kern.daten.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import de.seleri.kern.daten.entities.singles.KategorieEntity
import de.seleri.kern.daten.entities.singles.SpielEntity

@Entity(
  tableName = "SpielXKategorie",
  primaryKeys = ["spielID", "kategorieID"],
  foreignKeys = [ForeignKey(
    entity = SpielEntity::class,
    parentColumns = ["id"],
    childColumns = ["spielID"],
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = KategorieEntity::class,
    parentColumns = ["id"],
    childColumns = ["kategorieID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class SpielXKategorie(
  val spielID: Int,
  val kategorieID: Int,
)
