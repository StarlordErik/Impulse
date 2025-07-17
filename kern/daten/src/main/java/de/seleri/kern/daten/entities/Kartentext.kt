package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Herkunft

@Entity(
  tableName = "Kartentexte",
  foreignKeys = [ForeignKey(
    entity = Lokalisierung::class,
    parentColumns = ["id"],
    childColumns = ["lokalisierungsID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class Kartentext(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val lokalisierungsID: Int,
  val ausgeblendet: Boolean,

  val herkunft: Herkunft,

  val gesehen: Boolean,
)
