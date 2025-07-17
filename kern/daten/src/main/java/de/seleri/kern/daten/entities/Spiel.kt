package de.seleri.kern.daten.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Spielelement

@Entity(tableName = "Spiele")
data class Spiel(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val spielelement: Spielelement,

  val texteProKarte: Int = 1,
  val bildDateiname: String?,
)
