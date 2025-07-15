package de.seleri.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seleri.backend.Datenbanksystem
import de.seleri.backend.Kategorie
import de.seleri.backend.SammlungAnSpielelementen
import de.seleri.backend.Spiel
import de.seleri.backend.Sprachen
import de.seleri.backend.finde
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class ImpulseViewModel @Inject constructor(
    private val dbs: Datenbanksystem
): ViewModel() {
    val spiele = dbs.spiele.toList()

    fun getSpiel(id: Int): Spiel = dbs.spiele.finde(id)

    fun getName(sammlung: SammlungAnSpielelementen<*>): String =
        sammlung.localizations[Sprachen.OG]!!

    fun getRandomKartentext(kategorie: Kategorie): String {
        return dbs.getRandomKartentext(kategorie)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatenbanksystem(@ApplicationContext context: Context): Datenbanksystem {
        return Datenbanksystem.generieren(context)
    }
}
