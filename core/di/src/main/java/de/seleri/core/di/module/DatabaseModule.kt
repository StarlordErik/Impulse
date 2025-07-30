package de.seleri.core.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.AppDatabase
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.data.daos.TranslationDao
import de.seleri.core.data.daos.spielelemente.KartentextDao
import de.seleri.core.data.daos.spielelemente.KategorieDao
import de.seleri.core.data.daos.spielelemente.SpielDao
import de.seleri.core.di.utils.JsonDataLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
			.addCallback(callback)
			.build()
	}

	@Provides
	@Singleton
	fun provideRoomCallback(
		context: Context,
		spielDao: SpielDao,
		kategorieDao: KategorieDao,
		kartentextDao: KartentextDao,
		lokalisierungDao: LokalisierungDao,
		translationDao: TranslationDao,
		kategorieXkartentextDao: KategorieXkartentextDao,
		spielXkategorieDao: SpielXkategorieDao
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

	@Provides
	fun provideLokalisierungDao(db: AppDatabase): LokalisierungDao =
		db.lokalisierungDao()

	@Provides
	fun provideKartentextDao(db: AppDatabase): KartentextDao =
		db.kartentextDao()

	@Provides
	fun provideKategorieDao(db: AppDatabase): KategorieDao =
		db.kategorieDao()

	@Provides
	fun provideSpielDao(db: AppDatabase): SpielDao =
		db.spielDao()
}
