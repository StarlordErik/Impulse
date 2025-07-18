package de.seleri.frontend.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Icons, die auf [Screens.SpielScreen] dargestellt werden
 *
 * @property ressource das angezeigte Bild
 * @property beschreibung eine scheinbar notwendige Beschreibung des Bildes
 */
sealed class SpielScreenIcons(
  @param:DrawableRes val ressource: Int, @param:StringRes val beschreibung: Int,
) {

  /**
   * Einstellungsrädchen für die Einstellungen
   */
  object Einstellungsrad: SpielScreenIcons(
    de.seleri.frontend.R.drawable.settings_for_game_mechanics,
    de.seleri.frontend.R.string.settings_for_game_mechanics
  )

  /**
   * um zur letzten Karte zurückzukehren
   */
  object PfeilFuerLetzteKarte: SpielScreenIcons(
    de.seleri.frontend.R.drawable.arrow_back, de.seleri.frontend.R.string.arrow_back
  )

  /**
   * angezeigte Karte als gelöscht markieren
   */
  object KarteLoeschen: SpielScreenIcons(
    de.seleri.frontend.R.drawable.remove_card, de.seleri.frontend.R.string.remove_card
  )
}
