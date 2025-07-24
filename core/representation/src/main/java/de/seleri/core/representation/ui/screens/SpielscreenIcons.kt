package de.seleri.core.representation.ui.screens

import androidx.annotation.DrawableRes
import de.seleri.core.representation.R


sealed class SpielscreenIcons(
	@param:DrawableRes
	val ressource: Int
) {

	/**
	 * Einstellungsrädchen für die Einstellungen
	 */
	object Einstellungsrad: SpielscreenIcons(
		R.drawable.settings_for_game_mechanics
	)

	/**
	 * um zur letzten Karte zurückzukehren
	 */
	object PfeilFuerLetzteKarte: SpielscreenIcons(
		R.drawable.arrow_back
	)

	/**
	 * angezeigte Karte als gelöscht markieren
	 */
	object KarteLoeschen: SpielscreenIcons(
		R.drawable.remove_card
	)
}
