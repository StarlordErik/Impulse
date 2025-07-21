package de.seleri.core.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.domain.repositories.SpielRepo
import de.seleri.core.domain.usecases.GetAlleSpielMetasUsecase

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

	@Provides
	fun provideGetAlleSpielMetasUsecase(spielRepo: SpielRepo): GetAlleSpielMetasUsecase =
		GetAlleSpielMetasUsecase(spielRepo)
}
