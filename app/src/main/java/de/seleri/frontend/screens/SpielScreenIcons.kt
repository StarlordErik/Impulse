package de.seleri.frontend.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class SpielScreenIcons(
    @param:DrawableRes val ressource: Int, @param:StringRes val beschreibung: Int
) {
    object Einstellungsrad: SpielScreenIcons(
        de.seleri.frontend.R.drawable.settings_for_game_mechanics,
        de.seleri.frontend.R.string.settings_for_game_mechanics
    )

    object PfeilFuerLetzteKarte: SpielScreenIcons(
        de.seleri.frontend.R.drawable.arrow_back, de.seleri.frontend.R.string.arrow_back
    )

    object KarteLoeschen: SpielScreenIcons(
        de.seleri.frontend.R.drawable.remove_card, de.seleri.frontend.R.string.remove_card
    )
}
