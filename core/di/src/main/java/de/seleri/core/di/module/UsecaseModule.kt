package de.seleri.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.domain.repositories.SpielRepo
import de.seleri.core.domain.useCases.GetAllSpielMetaObjekteUC

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

	@Provides
	fun provideGetAlleSpielMetasUsecase(spielRepo: SpielRepo): GetAllSpielMetaObjekteUC =
		GetAllSpielMetaObjekteUC(spielRepo)
}
