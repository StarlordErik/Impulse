package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spiele")
data class Spiel(
  @PrimaryKey(autoGenerate = true) val id: Int,
  private val lokalisierungsID: Int,
  val nameID: Int = lokalisierungsID,
  val bildURL: String,
  val texteProKarte: Int,
)
