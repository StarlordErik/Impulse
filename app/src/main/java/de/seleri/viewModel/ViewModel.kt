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
import de.seleri.backend.SammlungAnSpielelementen
import de.seleri.backend.Spiel
import de.seleri.backend.Sprachen
import de.seleri.backend.finde
import de.seleri.frontend.screens.Screens
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Hilt benötigt diese Klasse, um bestimmte Werte über die gesamte Laufzeit der App zur Verfügung zu stellen,
 * unabhängig von der Lebensdauer einer Activity oder eines [Screens].
 */
@HiltViewModel
class ImpulseViewModel @Inject constructor(
  private val dbs: Datenbanksystem,
): ViewModel() {

  /**
   * Liste aller Spiele; [Datenbanksystem.spiele]
   */
  val spiele = dbs.spiele.toList()

  /**
   * findet ein [Spiel] anhand seiner [id]
   *
   * @param id ID des Spiels in [Spiel.id]
   */
  fun getSpiel(id: Int): Spiel = dbs.spiele.finde(id)

  /**
   * erzeugt den Namen einer gegebenen [sammlung]
   *
   * @param sammlung [SammlungAnSpielelementen], dessen Name erzeugt werden soll
   * @return Name der [sammlung]
   */
  fun getName(sammlung: SammlungAnSpielelementen<*>): String = sammlung.localizations[Sprachen.OG]!!

  /**
   * gibt einen zufälligen Kartentext aus einer [sammlung] zurück
   *
   * @param sammlung [SammlungAnSpielelementen], aus der ein Kartentext ausgewählt werden soll
   * @return zufälliger Kartentext aus der [sammlung]
   */
  fun getRandomKartentext(sammlung: SammlungAnSpielelementen<*>): String {
    return dbs.getRandomKartentext(sammlung)
  }
}

/**
 * Hilt benötigt diese Klasse, um bestimmte Werte über die gesamte Laufzeit der App zur Verfügung zu stellen,
 * unabhängig von der Lebensdauer einer Activity oder eines [Screens].
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  /**
   * stellt das [Datenbanksystem] bereit
   *
   * @param context Kontext der App
   * @return das [Datenbanksystem], erstellt auf Basis der Datenbankdatei
   */
  @Provides
  @Singleton
  fun provideDatenbanksystem(@ApplicationContext context: Context): Datenbanksystem {
    return Datenbanksystem.generieren(context)
  }
}

