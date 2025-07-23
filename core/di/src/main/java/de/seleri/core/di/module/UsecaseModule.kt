package de.seleri.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.seleri.core.domain.repositories.SpielRepo
import de.seleri.core.domain.useCases.GetAllSpielMetaObjekteUC
import de.seleri.core.domain.useCases.GetSpielUC

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {

	@Provides
	fun provideGetAlleSpielMetasUC(spielRepo: SpielRepo): GetAllSpielMetaObjekteUC =
		GetAllSpielMetaObjekteUC(spielRepo)

	@Provides
	fun provideGetSpielUC(spielRepo: SpielRepo): GetSpielUC =
		GetSpielUC(spielRepo)
}
