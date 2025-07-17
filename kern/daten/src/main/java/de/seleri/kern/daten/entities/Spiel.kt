package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Herkunft

@Entity(tableName = "Spiele")
data class Spiel(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val herkunft: Herkunft = Herkunft.OG,
  val ausgeblendet: Boolean = false,

  val texteProKarte: Int = 1,
  val bildDateiname: String?,
)
