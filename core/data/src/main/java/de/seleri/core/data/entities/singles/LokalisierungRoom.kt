package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.core.common.Sprache
import de.seleri.core.common.id.LokalisierungID
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "lokalisierungen")
data class LokalisierungRoom(
	@PrimaryKey(autoGenerate = true)
	override val lokalisierungID: Int,

	val ogSprache: Sprache
): LokalisierungStern {

	override val id: LokalisierungID get() = LokalisierungID(lokalisierungID)
}
