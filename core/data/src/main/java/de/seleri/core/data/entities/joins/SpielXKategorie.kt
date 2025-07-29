package de.seleri.core.data.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom

@Entity(
	tableName = "SpielXKategorie", primaryKeys = ["spielID", "kategorieID"], foreignKeys = [ForeignKey(
		entity = SpielRoom::class, parentColumns = ["id"], childColumns = ["spielID"], onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KategorieRoom::class,
		parentColumns = ["id"],
		childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("spielID"), Index("kategorieID")]
)
data class SpielXKategorie(
	val spielID: Int,
	val kategorieID: Int,
) : JoinEntity
