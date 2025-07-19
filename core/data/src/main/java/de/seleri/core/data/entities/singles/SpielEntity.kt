package de.seleri.core.data.entities.singles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spiele")
data class SpielEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val basis: BasisEntity,

  val texteProKarte: Int = 1,
  val bildDateiname: String? = null,
)
