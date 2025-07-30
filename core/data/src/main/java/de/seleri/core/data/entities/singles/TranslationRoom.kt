package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import de.seleri.core.common.Sprache
import de.seleri.core.common.id.LokalisierungID
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "translationen", primaryKeys = ["lokalisierungID", "sprache"], foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class,
		parentColumns = ["id"], childColumns = ["lokalisierungID"], onDelete = ForeignKey.Companion.CASCADE
	)], indices = [Index(value = ["lokalisierungID"])]
)
data class TranslationRoom(

	override val lokalisierungID: LokalisierungID,

	val sprache: Sprache,

	val bezeichnung: String,

	val bearbeitet: Boolean,
): LokalisierungStern {

	override val id: LokalisierungID get() = lokalisierungID
}
