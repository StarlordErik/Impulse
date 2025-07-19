package de.seleri.kern.daten.entities.singles

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Sprache

@Entity(
  tableName = "Lokalisierungen",
  foreignKeys = [ForeignKey(
    entity = Spiel::class,
    parentColumns = ["id"],
    childColumns = ["spielId"],
    onDelete = ForeignKey.Companion.CASCADE
  ), ForeignKey(
    entity = Kategorie::class,
    parentColumns = ["id"],
    childColumns = ["kategorieId"],
    onDelete = ForeignKey.Companion.CASCADE
  ), ForeignKey(
    entity = Kartentext::class,
    parentColumns = ["id"],
    childColumns = ["kartentextId"],
    onDelete = ForeignKey.Companion.CASCADE
  )]
)
data class Lokalisierung(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  val bezeichnung: String,
  val sprache: Sprache = Sprache.OG,
  val bearbeitet: Boolean = false,

  val spielID: Int? = null,
  val kategorieID: Int? = null,
  val kartentextID: Int? = null,
)
