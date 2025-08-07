package de.seleri.core.data.entities.joins

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.entities.joins.SpielXKategorieEntity
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "spiel_x_kategorie", primaryKeys = ["spielID", "kategorieID"], foreignKeys = [ForeignKey(
		entity = SpielRoom::class, parentColumns = ["id"], childColumns = ["spielID"], onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["id"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("spielID"), Index("kategorieID")]
)
data class SpielXKategorieRoom(

	@ColumnInfo(name = "spielID")
	@SerialName(value = "spielID")
	override val sammlungID: SpielID,

	@ColumnInfo(name = "kategorieID")
	@SerialName(value = "kategorieID")
	override val bestandteilID: KategorieID,
): JoinRoom, SpielXKategorieEntity
