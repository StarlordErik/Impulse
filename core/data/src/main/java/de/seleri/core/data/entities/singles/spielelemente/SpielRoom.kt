package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "spiele", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class,
		parentColumns = ["id"],
		childColumns = ["lokalisierungID"],
		onDelete = ForeignKey.CASCADE
	)]
)
data class SpielRoom(
	@PrimaryKey
	override val lokalisierungID: Int,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val bildDateiname: String?,

	val anleitung: String?,
	val texteProKarte: Int,
): SpielelementRoom
