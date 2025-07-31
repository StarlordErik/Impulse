package de.seleri.core.data

import androidx.room.TypeConverter
import de.seleri.core.common.Sprache
import de.seleri.core.common.id.EntityID
import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID

class Converters {

	@TypeConverter
	fun fromSprache(sprache: Sprache): Int =
		sprache.id

	@TypeConverter
	fun toSprache(spracheID: Int): Sprache =
		Sprache.fromId(spracheID)

	@TypeConverter
	fun fromEntityID(entityID: EntityID): Int =
		entityID.id

	@TypeConverter
	fun toLokalisierungID(lokalisierungID: Int): LokalisierungID =
		LokalisierungID(lokalisierungID)

	@TypeConverter
	fun toKartentextID(kartentextID: Int): KartentextID =
		KartentextID(kartentextID)

	@TypeConverter
	fun toKategorieID(kategorieID: Int): KategorieID =
		KategorieID(kategorieID)

	@TypeConverter
	fun toSpielID(spielID: Int): SpielID =
		SpielID(spielID)
}
