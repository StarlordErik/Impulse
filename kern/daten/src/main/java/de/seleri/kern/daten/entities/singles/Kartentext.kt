package de.seleri.kern.daten.entities.singles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kartentexte")
data class Kartentext(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val spielelementAttribute: SpielelementAttribute,

  @Embedded
  val favorisierbar: Favorisierbar,

  val gesehen: Boolean = false,
  val besprochen: Boolean = false,
)
