package de.seleri.core.data.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntityRoom

@Entity(
	tableName = "KategorieXKartentext", primaryKeys = ["kategorieID", "kartentextID"], foreignKeys = [ForeignKey(
		entity = KategorieEntityRoom::class,
		parentColumns = ["id"],
		childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KartentextEntityRoom::class,
		parentColumns = ["id"],
		childColumns = ["kartentextID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("kategorieID"), Index("kartentextID")]
)
data class KategorieXKartentext(
	val kategorieID: Int,
	val kartentextID: Int,
) : JoinEntity
