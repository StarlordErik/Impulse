package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import de.seleri.core.common.Sprache
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntityRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielEntityRoom

@Entity(
	tableName = "Lokalisierungen", foreignKeys = [ForeignKey(
		entity = SpielEntityRoom::class, parentColumns = ["id"], childColumns = ["spielID"],
		onDelete = ForeignKey.Companion.CASCADE
	), ForeignKey(
		entity = KategorieEntityRoom::class, parentColumns = ["id"], childColumns = ["kategorieID"],
		onDelete = ForeignKey.Companion.CASCADE
	), ForeignKey(
		entity = KartentextEntityRoom::class, parentColumns = ["id"], childColumns = ["kartentextID"],
		onDelete = ForeignKey.Companion.CASCADE
	)], indices = [ // @formatter:off
		Index("spielID"),
		Index("kategorieID"),
		Index("kartentextID"),
		Index(value = ["kartentextID", "sprache"], unique = true)
	] // @formatter:on
)
data class LokalisierungEntityRoom(
	@PrimaryKey(autoGenerate = true)
	override val id: Int,

	val bezeichnung: String,
	val sprache: Sprache,
	val bearbeitet: Boolean,

	val spielID: Int? = null,
	val kategorieID: Int? = null,
	val kartentextID: Int? = null,
) : EntityRoom
