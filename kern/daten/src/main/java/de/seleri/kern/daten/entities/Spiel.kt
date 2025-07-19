package de.seleri.kern.daten.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spiele")
data class Spiel(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val spielelementAttribute: SpielelementAttribute,

  val texteProKarte: Int = 1,
  val bildDateiname: String?,
)
