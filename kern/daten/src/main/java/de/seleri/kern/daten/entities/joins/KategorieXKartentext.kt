package de.seleri.kern.daten.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import de.seleri.kern.daten.entities.singles.KartentextEntity
import de.seleri.kern.daten.entities.singles.KategorieEntity

@Entity(
  tableName = "KategorieXKartentext",
  primaryKeys = ["kategorieID", "kartentextID"],
  foreignKeys = [ForeignKey(
    entity = KategorieEntity::class,
    parentColumns = ["id"],
    childColumns = ["kategorieID"],
    onDelete = ForeignKey.CASCADE
  ), ForeignKey(
    entity = KartentextEntity::class,
    parentColumns = ["id"],
    childColumns = ["kartentextID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class KategorieXKartentext(
  val kategorieID: Int,
  val kartentextID: Int,
)
