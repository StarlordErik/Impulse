package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kartentexte", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class, parentColumns = ["lokalisierungID"], childColumns = ["kartentextID"],
		onDelete = ForeignKey.CASCADE
	)]
)
data class KartentextRoom(
	@PrimaryKey
	@ColumnInfo(name = "kartentextID")
	override val lokalisierungID: Int,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val gesehen: Boolean,
	val besprochen: Boolean,
): SpielelementRoom {

	override val id: KartentextID get() = KartentextID(lokalisierungID)
}
