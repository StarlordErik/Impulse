package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Sprache

@Entity(tableName = "Lokalisierungen")
data class Lokalisierung(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  val sprache: Sprache,
  val bezeichnung: String,
  val veraendert: Boolean,
)
