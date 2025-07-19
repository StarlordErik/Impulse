package de.seleri.core.data.entities.singles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kategorien")
data class KategorieEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Int,

  @Embedded
  val basis: BasisEntity,
)
