package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import de.seleri.core.common.Sprache
import de.seleri.core.common.id.LokalisierungID
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "translationen", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class,
		parentColumns = ["id"],
		childColumns = ["lokalisierungID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index(value = ["lokalisierungID", "sprache"], unique = true)]
)
data class TranslationRoom(
	@PrimaryKey(autoGenerate = false) // nicht autogeneriert, da deterministisch über LokalisierungID feststellbar
	override val id: Int,

	val lokalisierungID: LokalisierungID,

	val sprache: Sprache,

	val bezeichnung: String,

	val bearbeitet: Boolean
): EntityRoom

