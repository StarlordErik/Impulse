package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Spiele")
data class SpielRoom(
	@PrimaryKey(autoGenerate = true)
	override val id: Int,

	@Embedded
	override val spielelementBasis: SpielelementBasis,

	val anleitung: String?,
	val texteProKarte: Int,
	val bildDateiname: String?,
): SpielelementRoom
