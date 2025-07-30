package de.seleri.core.data.entities.singles.lokStern.spielelemente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kategorien", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class, parentColumns = ["id"], childColumns = ["id"],
		onDelete = ForeignKey.CASCADE
	)]
)
data class KategorieRoom(
	@PrimaryKey
	@ColumnInfo(name = "id")
	override val lokalisierungID: LokalisierungID,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,
): SpielelementRoom {

	override val id: KategorieID get() = KategorieID(lokalisierungID.id)
}
