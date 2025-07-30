package de.seleri.core.data.entities.joins

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.id.spielelementID.BestandteilID
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SammlungID
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kategorie_x_kartentext", primaryKeys = ["kategorieID", "kartentextID"], foreignKeys = [ForeignKey(
		entity = KategorieRoom::class, parentColumns = ["kategorieID"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KartentextRoom::class, parentColumns = ["kartentextID"], childColumns = ["kartentextID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("kategorieID")]
)
data class KategorieXKartentextRoom(

	@ColumnInfo(name = "kategorieID")
	override val firstID: Int,

	@ColumnInfo(name = "kartentextID")
	override val secondID: Int,
): JoinRoom {

	override val sammlungID: SammlungID get() = kategorieID
	override val bestandteilID: BestandteilID get() = kartentextID

	val kategorieID: KategorieID get() = KategorieID(firstID)
	val kartentextID: KartentextID get() = KartentextID(secondID)
}
