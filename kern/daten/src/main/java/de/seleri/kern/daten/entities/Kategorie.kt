package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Herkunft

@Entity(tableName = "Kategorien")
data class Kategorie(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val herkunft: Herkunft,
  val ausgeblendet: Boolean,
)
