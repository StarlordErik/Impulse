package de.seleri.core.data.entities.singles.lokStern.spielelemente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kartentexte", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class, parentColumns = ["id"], childColumns = ["id"],
		onDelete = ForeignKey.CASCADE
	)]
)
data class KartentextRoom(
	@PrimaryKey
	@ColumnInfo(name = "id")
	override val lokalisierungID: LokalisierungID,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	val gesehen: Boolean,
	val besprochen: Boolean,
): SpielelementRoom, BestandteilRoom {

	override val id: KartentextID get() = KartentextID(lokalisierungID.id)
}
