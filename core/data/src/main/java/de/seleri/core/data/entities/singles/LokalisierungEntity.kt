package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import de.seleri.core.common.Sprache

@Entity(
	tableName = "Lokalisierungen", foreignKeys = [ForeignKey(
		entity = SpielEntity::class,
		parentColumns = ["id"], childColumns = ["spielID"],
		onDelete = ForeignKey.Companion.CASCADE
	), ForeignKey(
		entity = KategorieEntity::class,
		parentColumns = ["id"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.Companion.CASCADE
	), ForeignKey(
		entity = KartentextEntity::class,
		parentColumns = ["id"], childColumns = ["kartentextID"],
		onDelete = ForeignKey.Companion.CASCADE
	)]
)
data class LokalisierungEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int,

	val bezeichnung: String,
	val sprache: Sprache,
	val bearbeitet: Boolean,

	val spielID: Int? = null,
	val kategorieID: Int? = null,
	val kartentextID: Int? = null,
)
