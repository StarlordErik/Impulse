package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.Sprache

@Entity(
  tableName = "Lokalisierungen",
  foreignKeys = [ForeignKey(
    entity = SpielEntity::class,
    parentColumns = ["id"],
    childColumns = ["spielId"],
    onDelete = ForeignKey.Companion.CASCADE
  ), ForeignKey(
    entity = KategorieEntity::class,
    parentColumns = ["id"],
    childColumns = ["kategorieId"],
    onDelete = ForeignKey.Companion.CASCADE
  ), ForeignKey(
    entity = KartentextEntity::class,
    parentColumns = ["id"],
    childColumns = ["kartentextId"],
    onDelete = ForeignKey.Companion.CASCADE
  )]
)
data class LokalisierungEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  val bezeichnung: String,
  val sprache: Sprache = Sprache.OG,
  val bearbeitet: Boolean = false,

  val spielID: Int? = null,
  val kategorieID: Int? = null,
  val kartentextID: Int? = null,
)
