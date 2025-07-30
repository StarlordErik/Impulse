package de.seleri.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.data.daos.spielelemente.KartentextDao
import de.seleri.core.data.daos.spielelemente.KategorieDao
import de.seleri.core.data.daos.spielelemente.SpielDao
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom
import de.seleri.core.data.entities.joins.SpielXKategorieRoom
import de.seleri.core.data.entities.singles.LokalisierungRoom
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom
import de.seleri.core.data.entities.singles.spielelemente.SpielRoom

@TypeConverters(Converters::class)
@Database(
	entities = [
		LokalisierungRoom::class,
		KartentextRoom::class,
		KategorieRoom::class,
		SpielRoom::class,
		KategorieXKartentextRoom::class,
		SpielXKategorieRoom::class,
	], version = 1
)
abstract class AppDatabase: RoomDatabase() {

	abstract fun lokalisierungDao(): LokalisierungDao
	abstract fun kartentextDao(): KartentextDao
	abstract fun kategorieDao(): KategorieDao
	abstract fun spielDao(): SpielDao/*
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
