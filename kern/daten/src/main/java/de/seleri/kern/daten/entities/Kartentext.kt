package de.seleri.kern.daten.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kartentexte")
data class Kartentext(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val spielelementAttribute: SpielelementAttribute,

  val gesehen: Boolean,
)
