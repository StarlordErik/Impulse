package de.seleri.core.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.repository.implementations.LokalisierungImpl

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	fun provideLokalisierungRepo(
		dao: LokalisierungDao
	): LokalisierungRepo {
		return LokalisierungImpl(dao)
	}

	// TODO die anderen RepoImpls
}
