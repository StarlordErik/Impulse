package de.seleri.core.di.module

import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
import de.seleri.core.data.implementations.KartentextImpl
import de.seleri.core.data.implementations.KategorieImpl
import de.seleri.core.data.implementations.LokalisierungImpl
import de.seleri.core.data.implementations.SpielImpl
import de.seleri.core.data.implementations.TranslationImpl
import de.seleri.core.domain.repositories.TranslationRepo
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KategorieRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideTranslationRepo(
		dao: TranslationDAO
	): TranslationRepo =
		TranslationImpl(dao)

	@Provides
	@Singleton
	fun provideLokalisierungRepo(
		dao: LokalisierungDAO, translationRepo: TranslationRepo
	): LokalisierungRepo =
		LokalisierungImpl(dao, translationRepo)

	@Provides
	@Singleton
	fun provideKartentextRepo(
		dao: KartentextDAO,
		lokalisierungRepo: LokalisierungRepo,
		kategorieRepo: Lazy<KategorieRepo>,
		spielRepo: Lazy<SpielRepo>
	): KartentextRepo =
		KartentextImpl(dao, lokalisierungRepo, kategorieRepo, spielRepo)

	@Provides
	@Singleton
	fun provideKategorieRepo(
		dao: KategorieDAO,
		joinDao: KategorieXKartentextDAO,
		relationDao: KategorieMitKartentextenDAO,
		lokalisierungRepo: LokalisierungRepo,
		kartentextRepo: KartentextRepo, spielRepo: Lazy<SpielRepo>
	): KategorieRepo =
		KategorieImpl(dao, joinDao, relationDao, lokalisierungRepo, kartentextRepo, spielRepo)

	@Provides
	@Singleton
	fun provideSpielRepo(
		dao: SpielDAO,
		joinDao: SpielXKategorieDAO,
		relationDao: SpielMitKategorienDAO,
		lokalisierungRepo: LokalisierungRepo,
		kartentextRepo: KartentextRepo,
		kategorieRepo: KategorieRepo
	): SpielRepo =
		SpielImpl(dao, joinDao, relationDao, lokalisierungRepo, kartentextRepo, kategorieRepo)
}
