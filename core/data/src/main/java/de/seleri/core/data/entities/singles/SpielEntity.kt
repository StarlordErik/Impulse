package de.seleri.core.data.entities.singles

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spiele")
data class SpielEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int,

	@Embedded
	val spielelementBasis: SpielelementBasis,

	val texteProKarte: Int,
	val bildDateiname: String?,
)
