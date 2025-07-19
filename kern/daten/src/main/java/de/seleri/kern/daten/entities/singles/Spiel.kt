package de.seleri.kern.daten.entities.singles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spiele")
data class Spiel(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val spielelementBasis: SpielelementBasis,

  val texteProKarte: Int = 1,
  val bildDateiname: String? = null,
)
