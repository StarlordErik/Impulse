package de.seleri.core.data

import androidx.room.TypeConverter
import de.seleri.core.common.Sprache
import de.seleri.core.common.id.EntityID

class Converters {

	@TypeConverter
	fun fromEntityID(entityID: EntityID): Int =
		entityID.id

	@TypeConverter
	fun fromSprache(sprache: Sprache): Int =
		sprache.id

	@TypeConverter
	fun toSprache(spracheID: Int): Sprache =
		Sprache.fromId(spracheID)
}
