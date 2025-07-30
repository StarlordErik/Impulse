package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kategorien", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class,
		parentColumns = ["id"],
		childColumns = ["lokalisierungID"],
		onDelete = ForeignKey.CASCADE
	)]
)
data class KategorieRoom(
	@PrimaryKey
	override val lokalisierungID: Int,

	override val selbstErstellt: Boolean,
	override val inaktiv: Boolean,
	override val favorisiert: Boolean,
): SpielelementRoom {

	override val id: KategorieID get() = KategorieID(lokalisierungID)
}
