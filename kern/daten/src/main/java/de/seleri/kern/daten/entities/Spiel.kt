package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
  tableName = "Spiele",
  foreignKeys = [ForeignKey(
    entity = Lokalisierung::class,
    parentColumns = ["id"],
    childColumns = ["lokalisierungsID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class Spiel(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val lokalisierungsID: Int,
  val ausgeblendet: Boolean,

  val bildURL: String,
  val texteProKarte: Int,
)
