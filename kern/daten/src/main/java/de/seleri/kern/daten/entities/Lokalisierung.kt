package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lokalisierungen")
data class Lokalisierung(
  @PrimaryKey(autoGenerate = true) private val id: Int,
  private val lokalisierung: Map<Sprachen, String>,
  private val veraendert: Boolean,
)
