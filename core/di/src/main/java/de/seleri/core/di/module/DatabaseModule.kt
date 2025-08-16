package de.seleri.core.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.daos.compositePk.TranslationDAO
import de.seleri.core.data.daos.compositePk.joins.KategorieXKartentextDAO
import de.seleri.core.data.daos.compositePk.joins.SpielXKategorieDAO
import de.seleri.core.data.daos.relations.KategorieMitKartentextenDAO
import de.seleri.core.data.daos.relations.SpielMitKategorienDAO
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.daos.singles.spielelemente.KartentextDAO
import de.seleri.core.data.daos.singles.spielelemente.KategorieDAO
import de.seleri.core.data.daos.singles.spielelemente.SpielDAO
import de.seleri.core.data.database.AppDatabase
import de.seleri.core.data.database.Callback
import de.seleri.core.domain.useCases.create.NewKategorieUC
import de.seleri.core.domain.useCases.create.NewSpielUC
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

	@Provides
	@Singleton
	fun provideDatabase(
		@ApplicationContext
		context: Context, newSpielUC: NewSpielUC, newKategorieUC: NewKategorieUC
	): AppDatabase {
		return Room
			.databaseBuilder(
				context, AppDatabase::class.java, "app.db"
			)
			.addCallback(Callback(context, newSpielUC, newKategorieUC))
			.build()
	}

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
