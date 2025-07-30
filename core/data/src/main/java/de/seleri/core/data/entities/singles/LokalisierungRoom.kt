package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.core.common.Sprache
import de.seleri.core.data.entities.EntityRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "lokalisierungen")
data class LokalisierungRoom(
	@PrimaryKey(autoGenerate = true)
	val id: Int,

	val ogSprache: Sprache
): EntityRoom
