package de.seleri.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.daos.singles.LokalisierungDao
import de.seleri.core.data.daos.singles.lokStern.spielelemente.KartentextDao
import de.seleri.core.data.daos.singles.lokStern.spielelemente.KategorieDao
import de.seleri.core.data.daos.singles.lokStern.spielelemente.SpielDao
import de.seleri.core.data.implementations.KartentextImpl
import de.seleri.core.data.implementations.KategorieImpl
import de.seleri.core.data.implementations.LokalisierungImpl
import de.seleri.core.data.implementations.SpielImpl
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.KategorieRepo
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.domain.repositories.SpielRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideLokalisierungRepo(
		dao: LokalisierungDao
	): LokalisierungRepo =
		LokalisierungImpl(dao)

	@Provides
	@Singleton
	fun provideKartentextRepo(
		dao: KartentextDao, lokalisierungRepo: LokalisierungRepo
	): KartentextRepo =
		KartentextImpl(dao, lokalisierungRepo)

	@Provides
	@Singleton
	fun provideKategorieRepo(
		dao: KategorieDao, lokalisierungRepo: LokalisierungRepo, kartentextRepo: KartentextRepo
	): KategorieRepo =
		KategorieImpl(dao, lokalisierungRepo, kartentextRepo)

	@Provides
	@Singleton
	fun provideSpielRepo(
		dao: SpielDao, lokalisierungRepo: LokalisierungRepo, kategorieRepo: KategorieRepo
	): SpielRepo =
		SpielImpl(dao, lokalisierungRepo, kategorieRepo)
}
