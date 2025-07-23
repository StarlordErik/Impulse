package de.seleri.core.representation.ui.screens

import de.seleri.core.domain.model.idTypes.SpielelementID

sealed class Screen(val route: String) {

	object StartScreen: Screen(route = "start")

	class SpielScreen(spielID: SpielelementID.SpielID): Screen(route = "spiel/${spielID.toInt()}")
}
