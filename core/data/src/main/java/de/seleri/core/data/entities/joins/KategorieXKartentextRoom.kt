package de.seleri.core.data.entities.joins

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.entities.joins.KategorieXKartentextEntity
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KategorieRoom
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kategorie_x_kartentext", primaryKeys = ["kategorieID", "kartentextID"], foreignKeys = [ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["id"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KartentextRoom::class, parentColumns = ["id"], childColumns = ["kartentextID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("kategorieID")]
)
data class KategorieXKartentextRoom(

	@ColumnInfo(name = "kategorieID")
	@SerialName(value = "kategorieID")
	override val sammlungID: KategorieID,

	@ColumnInfo(name = "kartentextID")
	@SerialName(value = "kartentextID")
	override val bestandteilID: KartentextID,
): JoinRoom, KategorieXKartentextEntity

