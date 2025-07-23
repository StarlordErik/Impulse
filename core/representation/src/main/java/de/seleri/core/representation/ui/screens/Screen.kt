package de.seleri.core.representation.ui.screens

import de.seleri.core.domain.model.idTypes.SpielelementID

sealed class Screen(val route: String) {

	object Startscreen: Screen(route = "start")

	class Spielscreen(spielID: SpielelementID.SpielID): Screen(route = "spiel/${spielID.toInt()}")
}
