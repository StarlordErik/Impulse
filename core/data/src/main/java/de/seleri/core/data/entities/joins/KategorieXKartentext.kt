package de.seleri.core.data.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom

@Entity(
	tableName = "kategorie_x_kartentext", primaryKeys = ["kategorieID", "kartentextID"], foreignKeys = [ForeignKey(
		entity = KategorieRoom::class,
		parentColumns = ["id"],
		childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KartentextRoom::class,
		parentColumns = ["id"],
		childColumns = ["kartentextID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("kategorieID"), Index("kartentextID")]
)
data class KategorieXKartentext(
	val kategorieID: Int,
	val kartentextID: Int,
): JoinRoom
