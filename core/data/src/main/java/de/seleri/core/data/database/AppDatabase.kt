package de.seleri.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.seleri.core.data.daos.compositePk.TranslationDAO
import de.seleri.core.data.daos.compositePk.joins.KategorieXKartentextDAO
import de.seleri.core.data.daos.compositePk.joins.SpielXKategorieDAO
import de.seleri.core.data.daos.relations.KategorieMitKartentextenDAO
import de.seleri.core.data.daos.relations.SpielMitKategorienDAO
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.daos.singles.spielelemente.KartentextDAO
import de.seleri.core.data.daos.singles.spielelemente.KategorieDAO
import de.seleri.core.data.daos.singles.spielelemente.SpielDAO
import de.seleri.core.data.entities.TranslationRoom
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom
import de.seleri.core.data.entities.joins.SpielXKategorieRoom
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom

@TypeConverters(Converters::class)
@Database(
	entities = [
		TranslationRoom::class,
		LokalisierungRoom::class,
		KartentextRoom::class,
		KategorieRoom::class,
		SpielRoom::class,
		KategorieXKartentextRoom::class,
		SpielXKategorieRoom::class,
	], version = 1
)
abstract class AppDatabase: RoomDatabase() {

	abstract fun translationDAO(): TranslationDAO
	abstract fun lokalisierungDAO(): LokalisierungDAO
	abstract fun kartentextDAO(): KartentextDAO
	abstract fun kategorieDAO(): KategorieDAO
	abstract fun kategorieXKartentextDAO(): KategorieXKartentextDAO
	abstract fun kategorieMitKartentextenDAO(): KategorieMitKartentextenDAO
	abstract fun spielDAO(): SpielDAO
	abstract fun spielXKategorieDAO(): SpielXKategorieDAO
	abstract fun spielMitKategorienDAO(): SpielMitKategorienDAO
}
