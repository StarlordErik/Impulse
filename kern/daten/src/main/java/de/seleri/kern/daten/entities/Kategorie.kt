package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kategorien")
data class Kategorie(
  @PrimaryKey(autoGenerate = true) private val id: Int,
  private val lokalisierungsID: Int,
  val nameID: Int = lokalisierungsID,
)
