package de.seleri.core.data

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

	abstract fun translationDao(): TranslationDAO
	abstract fun lokalisierungDao(): LokalisierungDAO
	abstract fun kartentextDao(): KartentextDAO
	abstract fun kategorieDao(): KategorieDAO
	abstract fun kategorieXKartentextDao(): KategorieXKartentextDAO
	abstract fun kategorieMitKartentextenDao(): KategorieMitKartentextenDAO
	abstract fun spielDao(): SpielDAO
	abstract fun spielXKategorieDao(): SpielXKategorieDAO
	abstract fun spielMitKategorienDao(): SpielMitKategorienDAO

	/*
	companion object {

		private var INSTANCE: AppDatabase? = null


		fun getInstance(context: Context): AppDatabase {
			return INSTANCE
				?: synchronized(this) {
					val instance = Room
						.databaseBuilder(
							context.applicationContext, AppDatabase::class.java, "app_db"
						)
						.addCallback(object: Callback() {
							override fun onCreate(db: SupportSQLiteDatabase) {
								super.onCreate(db)
								// Starte Coroutine, um Daten einzulesen und einzufügen
								CoroutineScope(Dispatchers.IO).launch {
									val database = getInstance(context)

									fillDatabaseWithInitialData(context, database)
								}
							}
						})
						.build()
					INSTANCE = instance
					instance
				}
		}
	}

		 */
}/*
suspend fun fillDatabaseWithInitialData(context: Context, db: AppDatabase) {
	TODO
	val jsonString = context.assets.open("kartentexte.json").bufferedReader().use { it.readText() }
	val kartentexte: List<KartentextEntity> = parseJsonToEntities(jsonString) // z.B. mit Moshi oder Gson

	db.kartentextDao().insertAll(kartentexte)

}
*/
