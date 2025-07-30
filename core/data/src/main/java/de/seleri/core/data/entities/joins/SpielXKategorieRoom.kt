package de.seleri.core.data.entities.joins

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SammlungID
import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "spiel_x_kategorie", primaryKeys = ["spielID", "kategorieID"], foreignKeys = [ForeignKey(
		entity = SpielRoom::class, parentColumns = ["spielID"], childColumns = ["spielID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["kategorieID"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("spielID")]
)
data class SpielXKategorieRoom(
	@ColumnInfo(name = "spielID")
	override val firstID: Int,

	@ColumnInfo(name = "kategorieID")
	override val secondID: Int,
): JoinRoom {

	override val sammlungID: SammlungID get() = spielID
	override val bestandteilID: BestandteilID get() = kategorieID
	val spielID: SpielID get() = SpielID(firstID)
	val kategorieID: KategorieID get() = KategorieID(secondID)
}
