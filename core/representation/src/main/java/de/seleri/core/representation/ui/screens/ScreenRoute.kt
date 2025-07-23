package de.seleri.core.representation.ui.screens

import de.seleri.core.domain.model.idTypes.SpielelementID

sealed class ScreenRoute(val route: String) {

	object StartscreenRoute: ScreenRoute("start")

	class SpielscreenRoute(spielID: SpielelementID.SpielID): ScreenRoute("spiel/${spielID.toInt()}")
}
