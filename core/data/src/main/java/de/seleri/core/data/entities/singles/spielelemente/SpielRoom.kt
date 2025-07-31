package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.entities.singles.spielelemente.SpielEntity
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "spiele", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class, parentColumns = ["id"], childColumns = ["id"],
		onDelete = ForeignKey.CASCADE
	)]
)
data class SpielRoom(

	@PrimaryKey
	@ColumnInfo(name = "id")
	@SerialName(value = "id")
	override val lokalisierungID: LokalisierungID,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	override val bildDateiname: String?,

	override val anleitung: String?,
	override val texteProKarte: Int,
): SpielelementRoom, SpielEntity
