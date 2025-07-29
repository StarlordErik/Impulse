package de.seleri.core.data.entities.singles.spielelemente

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import de.seleri.core.data.entities.singles.LokalisierungRoom
import kotlinx.serialization.Serializable

@Serializable
@Entity(
	tableName = "kategorien", foreignKeys = [ForeignKey(
		entity = LokalisierungRoom::class,
		parentColumns = ["id"],
		childColumns = ["lokalisierungID"],
		onDelete = ForeignKey.CASCADE
	)], indices = [Index(value = ["lokalisierungID"], unique = true)]
)
data class KategorieRoom(
	@PrimaryKey(autoGenerate = true)
	override val id: Int,

	override val lokalisierungID: Int,

	@Embedded
	override val spielelementDatenRoom: SpielelementDatenRoom,
): SpielelementRoom
