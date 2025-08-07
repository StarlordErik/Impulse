package de.seleri.core.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.AppDatabase
import de.seleri.core.data.daos.compositePk.TranslationDAO
import de.seleri.core.data.daos.compositePk.joins.KategorieXKartentextDAO
import de.seleri.core.data.daos.compositePk.joins.SpielXKategorieDAO
import de.seleri.core.data.daos.relations.KategorieMitKartentextenDAO
import de.seleri.core.data.daos.relations.SpielMitKategorienDAO
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.daos.singles.spielelemente.KartentextDAO
import de.seleri.core.data.daos.singles.spielelemente.KategorieDAO
import de.seleri.core.data.daos.singles.spielelemente.SpielDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideDatabase(
		@ApplicationContext
		context: Context, callback: RoomDatabase.Callback
	): AppDatabase {
		return Room
			.databaseBuilder(
				context, AppDatabase::class.java, "app_db"
			)
			//.addCallback(callback)
			.build()
	}

	/*
		@Provides
		@Singleton
		fun provideRoomCallback(
			context: Context,
			spielDao: SpielDAO,
			kategorieDao: KategorieDAO,
			kartentextDao: KartentextDAO,
			lokalisierungDao: LokalisierungDAO,
			translationDao: TranslationDAO,
			kategorieXkartentextDao: KategorieXKartentextDAO,
			spielXkategorieDao: SpielXKategorieDAO
		): RoomDatabase.Callback {
			return object: RoomDatabase.Callback() {
				override fun onCreate(db: SupportSQLiteDatabase) {
					super.onCreate(db)
					CoroutineScope(Dispatchers.IO).launch {
						val loader = JsonDataLoader(context)

						spielDao.insertAll(loader.loadList("spiele.json"))
						kategorieDao.insertAll(loader.loadList("kategorien.json"))
						kartentextDao.insertAll(loader.loadList("kartentexte.json"))
						lokalisierungDao.insertAll(loader.loadList("lokalisierungen.json"))
						translationDao.insertAll(loader.loadList("translationen.json"))
						kategorieXkartentextDao.insertAll(loader.loadList("kategorie_x_kartentext.json"))
						spielXkategorieDao.insertAll(loader.loadList("spiel_x_kategorie.json"))
					}
				}
			}
		}
	 */

	@Provides
	fun provideTranslationDAO(db: AppDatabase): TranslationDAO =
		db.translationDAO()

	@Provides
	fun provideLokalisierungDAO(db: AppDatabase): LokalisierungDAO =
		db.lokalisierungDAO()

	@Provides
	fun provideKartentextDAO(db: AppDatabase): KartentextDAO =
		db.kartentextDAO()

	@Provides
	fun provideKategorieDAO(db: AppDatabase): KategorieDAO =
		db.kategorieDAO()

	@Provides
	fun provideKategorieXKartentextDAO(db: AppDatabase): KategorieXKartentextDAO =
		db.kategorieXKartentextDAO()

	@Provides
	fun provideKategorieMitKartentextenDAO(db: AppDatabase): KategorieMitKartentextenDAO =
		db.kategorieMitKartentextenDAO()

	@Provides
	fun provideSpielDAO(db: AppDatabase): SpielDAO =
		db.spielDAO()

	@Provides
	fun provideSpielXKategorieDAO(db: AppDatabase): SpielXKategorieDAO =
		db.spielXKategorieDAO()

	@Provides
	fun provideSpielMitKategorienDAO(db: AppDatabase): SpielMitKategorienDAO =
		db.spielMitKategorienDAO()
}
