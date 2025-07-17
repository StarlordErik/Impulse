package de.seleri.kern.daten.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.kern.daten.utils.Herkunft

@Entity(
  tableName = "Kategorien",
  foreignKeys = [ForeignKey(
    entity = Lokalisierung::class,
    parentColumns = ["id"],
    childColumns = ["lokalisierungsID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class Kategorie(
  @PrimaryKey(autoGenerate = true)
  val id: Int,
  val lokalisierungsID: Int,
  val herkunft: Herkunft,
  val ausgeblendet: Boolean,
)
