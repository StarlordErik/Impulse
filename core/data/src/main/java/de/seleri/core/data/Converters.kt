package de.seleri.core.data

import androidx.room.TypeConverter
import de.seleri.core.common.Sprache

class Converters {

	@TypeConverter
	fun fromSprache(sprache: Sprache): Int =
		sprache.id

	@TypeConverter
	fun toSprache(spracheID: Int): Sprache =
		Sprache.fromId(spracheID)
}
