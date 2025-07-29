package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kartentexte")
data class KartentextEntityRoom(
	@PrimaryKey(autoGenerate = true)
	override val id: Int,

	@Embedded
	override val spielelementBasis: SpielelementBasis,

	val gesehen: Boolean,
	val besprochen: Boolean,
) : SpielelementEntityRoom
