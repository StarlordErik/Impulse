package de.seleri.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.AppDatabase
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.data.daos.spielelemente.KartentextDao
import de.seleri.core.data.daos.spielelemente.KategorieDao
import de.seleri.core.data.daos.spielelemente.SpielDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideDatabase(
		@ApplicationContext
		context: Context
	): AppDatabase =
		Room
			.databaseBuilder(
				context, AppDatabase::class.java, "app_db"
			)
			.build()

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
