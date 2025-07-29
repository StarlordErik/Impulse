package de.seleri.core.data.entities.joins

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielEntityRoom

@Entity(
	tableName = "SpielXKategorie", primaryKeys = ["spielID", "kategorieID"], foreignKeys = [ForeignKey(
		entity = SpielEntityRoom::class, parentColumns = ["id"], childColumns = ["spielID"], onDelete = ForeignKey.CASCADE
	), ForeignKey(
		entity = KategorieEntityRoom::class,
		parentColumns = ["id"],
		childColumns = ["kategorieID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index("spielID"), Index("kategorieID")]
)
data class SpielXKategorie(
	val spielID: Int,
	val kategorieID: Int,
) : JoinEntity
