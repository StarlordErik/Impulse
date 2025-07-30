package de.seleri.core.data.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "spiel_x_kategorie", primaryKeys = ["firstID", "secondID"], foreignKeys = [ForeignKey(
		entity = SpielRoom::class,
		parentColumns = ["lokalisierungID"],
		childColumns = ["firstID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["lokalisierungID"], childColumns = ["secondID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("firstID")]
)
data class SpielXKategorieRoom(
	override val firstID: Int,
	override val secondID: Int,
): JoinRoom {

	val spielID: SpielID get() = SpielID(firstID)
	val kategorieID: KategorieID get() = KategorieID(secondID)
}
