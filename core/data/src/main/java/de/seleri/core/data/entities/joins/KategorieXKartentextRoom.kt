package de.seleri.core.data.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kategorie_x_kartentext", primaryKeys = ["firstID", "secondID"], foreignKeys = [ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["lokalisierungID"], childColumns = ["firstID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KartentextRoom::class, parentColumns = ["lokalisierungID"], childColumns = ["secondID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("firstID")]
)
data class KategorieXKartentextRoom(
	override val firstID: Int, override val secondID: Int
): JoinRoom {

	val kategorieID: KategorieID get() = KategorieID(firstID)
	val kartentextID: KartentextID get() = KartentextID(secondID)
}
