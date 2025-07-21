package de.seleri.core.domain.usecases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.domain.repositories.SpielRepo

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

	@Provides
	fun provideGetAlleSpielMetasUsecase(spielRepo: SpielRepo): GetAlleSpielMetasUsecase =
		GetAlleSpielMetasUsecase(spielRepo)
}
