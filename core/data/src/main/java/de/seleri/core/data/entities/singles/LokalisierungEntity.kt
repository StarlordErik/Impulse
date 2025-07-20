package de.seleri.core.data.entities.singles

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.seleri.core.common.LokalisierungVon
import de.seleri.core.common.Sprache

@Entity(tableName = "Lokalisierungen")
data class LokalisierungEntity(
	@PrimaryKey(autoGenerate = true)
	val id: Int,

	val bezeichnung: String,
	val sprache: Sprache = Sprache.OG,
	val bearbeitet: Boolean = false,

	val lokalisierungVon: LokalisierungVon,
	val spielelementID: Int,
)
