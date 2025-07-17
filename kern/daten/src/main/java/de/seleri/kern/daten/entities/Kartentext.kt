package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kartentexte")
data class Kartentext(
  @PrimaryKey(autoGenerate = true) private val id: Int,
  private val lokalisierungsID: Int,
  val textID: Int = lokalisierungsID,
  private val gesehen: Boolean,
  private val geloescht: Boolean,
)
