package de.seleri.core.data

import androidx.room.TypeConverter
import de.seleri.core.common.Sprache
import de.seleri.core.common.id.EntityID
import de.seleri.core.common.id.LokalisierungID
import de.seleri.core.common.id.TranslationID
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID

class Converters {

	@TypeConverter
	fun fromEntityID(entityID: EntityID): Int =
		entityID.id

	@TypeConverter
	fun toTranslationID(translationID: Int): TranslationID =
		TranslationID(translationID)

	@TypeConverter
	fun toLokalisierungID(lokalisierungID: Int): LokalisierungID =
		LokalisierungID(lokalisierungID)

	@TypeConverter
	fun toKartentextID(kartentextID: KartentextID): Int =
		kartentextID.id

	@TypeConverter
	fun toKategorieID(kategorieID: KategorieID): Int =
		kategorieID.id

	@TypeConverter
	fun toSpielID(spielID: SpielID): Int =
		spielID.id

	@TypeConverter
	fun fromSprache(sprache: Sprache): Int =
		sprache.id

	@TypeConverter
	fun toSprache(spracheID: Int): Sprache =
		Sprache.fromId(spracheID)
}
