package de.seleri.core.data.entities.joins

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.lokStern.spielelemente.SpielRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "spiel_x_kategorie", primaryKeys = ["spielID", "kategorieID"], foreignKeys = [ForeignKey(
		entity = SpielRoom::class, parentColumns = ["id"], childColumns = ["spielID"], onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["id"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("spielID")]
)
data class SpielXKategorieRoom(
	@ColumnInfo(name = "spielID")
	override val sammlungID: SpielID,

	@ColumnInfo(name = "kategorieID")
	override val bestandteilID: KategorieID,
): JoinRoom {

	val spielID: SpielID get() = sammlungID
	val kategorieID: KategorieID get() = bestandteilID
}
