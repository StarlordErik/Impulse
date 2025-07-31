package de.seleri.core.data.entities.singles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.core.common.Sprache
import de.seleri.core.common.entities.singles.LokalisierungEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "lokalisierungen")
data class LokalisierungRoom(

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	@SerialName(value = "id")
	override val lokalisierungID: Int,

	override val ogSprache: Sprache
): SingleRoom, LokalisierungEntity


