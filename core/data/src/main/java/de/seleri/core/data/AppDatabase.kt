package de.seleri.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.data.daos.spielelemente.KartentextDao
import de.seleri.core.data.daos.spielelemente.KategorieDao
import de.seleri.core.data.daos.spielelemente.SpielDao
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.data.entities.singles.LokalisierungEntity
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.data.entities.singles.spielelemente.SpielEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
}

suspend fun fillDatabaseWithInitialData(context: Context, db: AppDatabase) {
	TODO()	/*
	val jsonString = context.assets.open("kartentexte.json").bufferedReader().use { it.readText() }
	val kartentexte: List<KartentextEntity> = parseJsonToEntities(jsonString) // z.B. mit Moshi oder Gson

	db.kartentextDao().insertAll(kartentexte)
	 */
}
