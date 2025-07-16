package de.seleri.frontend.screens

import de.seleri.backend.Spiel

/**
 * enthält die Bildschirmoberflächen der App
 *
 * @property route Bezeichnung zur Bildschirmoberfläche, aus der der Pfad gebildet wird
 */
sealed class Screens(val route: String) {
  /**
   * Startbildschirm der App
   */
  object StartScreen: Screens("start")

  /**
   * Spielbildschirm eines jeden Spiels
   */
  object SpielScreen: Screens("spiel") {
    /**
     * erzeugt Pfad zum Spielbildschirm
     *
     * @param id [Spiel.id] des zu ladenden Spiels
     */
    fun mitDerID(id: Int): String {
      return "$route/$id"
    }
  }
}
