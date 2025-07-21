package de.seleri.core.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.KategorieRepo
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.domain.repositories.SpielRepo
import de.seleri.core.repository.implementations.KartentextImpl
import de.seleri.core.repository.implementations.KategorieImpl
import de.seleri.core.repository.implementations.LokalisierungImpl
import de.seleri.core.repository.implementations.SpielImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Binds
	abstract fun bindLokalisierungRepo(impl: LokalisierungImpl): LokalisierungRepo

	@Binds
	abstract fun bindKartentextRepo(impl: KartentextImpl): KartentextRepo

	@Binds
	abstract fun bindKategorieRepo(impl: KategorieImpl): KategorieRepo

	@Binds
	abstract fun bindSpielRepo(impl: SpielImpl): SpielRepo
}
