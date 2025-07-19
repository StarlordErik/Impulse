package de.seleri.kern.daten.entities.singles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kategorien")
data class Kategorie(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val spielelementAttribute: SpielelementAttribute,
)
