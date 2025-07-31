package de.seleri.core.data.entities.singles.lokStern.spielelemente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.entities.singles.lokStern.spielelemente.KartentextEntity
import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.SerialName
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
	@SerialName(value = "id")
	override val lokalisierungID: LokalisierungID,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,

	override val gesehen: Boolean,
	override val besprochen: Boolean,
): SpielelementRoom, BestandteilRoom, KartentextEntity
