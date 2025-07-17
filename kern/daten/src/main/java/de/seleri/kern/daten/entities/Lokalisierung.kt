package de.seleri.kern.daten.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import de.seleri.kern.daten.utils.Sprache

@Entity(tableName = "Lokalisierungen")
data class Lokalisierung(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  val veraendert: Boolean,
)

@Entity(
  tableName = "LokalisierungsEintrag",
  primaryKeys = ["lokalisierungsID", "sprache"],
  foreignKeys = [ForeignKey(
    entity = Lokalisierung::class,
    parentColumns = ["id"],
    childColumns = ["lokalisierungsID"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class LokalisierungsEintrag(
  val lokalisierungsID: Int,
  val sprache: Sprache,
  val bezeichnung: String,
)

data class LokalisierungMitEintragen(
  @Embedded
  val lokalisierung: Lokalisierung,

  @Relation(
    parentColumn = "id",
    entityColumn = "lokalisierungsID"
  )
  val lokalisierungsEintraege: List<LokalisierungsEintrag>,
)
