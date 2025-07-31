package de.seleri.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.daos.singles.lokStern.spielelemente.KartentextDAO
import de.seleri.core.data.daos.singles.lokStern.spielelemente.KategorieDAO
import de.seleri.core.data.daos.singles.lokStern.spielelemente.SpielDAO
import de.seleri.core.data.implementations.KartentextImpl
import de.seleri.core.data.implementations.KategorieImpl
import de.seleri.core.data.implementations.LokalisierungImpl
import de.seleri.core.data.implementations.SpielImpl
import de.seleri.core.domain.deprecatedRepositories.KartentextRepo
import de.seleri.core.domain.deprecatedRepositories.KategorieRepo
import de.seleri.core.domain.deprecatedRepositories.LokalisierungRepo
import de.seleri.core.domain.deprecatedRepositories.SpielRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideLokalisierungRepo(
		dao: LokalisierungDAO
	): LokalisierungRepo =
		LokalisierungImpl(dao)

	@Provides
	@Singleton
	fun provideKartentextRepo(
		dao: KartentextDAO, lokalisierungRepo: LokalisierungRepo
	): KartentextRepo =
		KartentextImpl(dao, lokalisierungRepo)

	@Provides
	@Singleton
	fun provideKategorieRepo(
		dao: KategorieDAO, lokalisierungRepo: LokalisierungRepo, kartentextRepo: KartentextRepo
	): KategorieRepo =
		KategorieImpl(dao, lokalisierungRepo, kartentextRepo)

	@Provides
	@Singleton
	fun provideSpielRepo(
		dao: SpielDAO, lokalisierungRepo: LokalisierungRepo, kategorieRepo: KategorieRepo
	): SpielRepo =
		SpielImpl(dao, lokalisierungRepo, kategorieRepo)
}
