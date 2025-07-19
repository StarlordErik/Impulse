package de.seleri.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import de.seleri.core.data.daos.KartentextDao
import de.seleri.core.data.daos.KategorieDao
import de.seleri.core.data.daos.KategorieXKartentextDao
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.data.daos.SpielDao
import de.seleri.core.data.daos.SpielXKategorieDao
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.KartentextEntity
import de.seleri.core.data.entities.singles.KategorieEntity
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.SpielEntity

@Database(
	entities = [
		LokalisierungEntity::class,
		KartentextEntity::class,
		KategorieEntity::class,
		SpielEntity::class,
		KategorieXKartentext::class,
		SpielXKategorie::class,
	], version = 1
)
abstract class AppDatabase: RoomDatabase() {
	abstract fun lokalisierungDao(): LokalisierungDao
	abstract fun kartentextDao(): KartentextDao
	abstract fun kategorieDao(): KategorieDao
	abstract fun spielDao(): SpielDao
	abstract fun kategorieXKartentextDao(): KategorieXKartentextDao
	abstract fun spielXKategorieDao(): SpielXKategorieDao
}
