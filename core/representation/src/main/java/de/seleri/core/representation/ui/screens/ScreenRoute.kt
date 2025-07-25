package de.seleri.core.representation.ui.screens

import de.seleri.core.common.idTypes.SpielelementID

sealed class ScreenRoute(val route: String) { object StartscreenRoute: ScreenRoute("start")
	object SpielscreenRoute: ScreenRoute("spiel") {

		fun mitDerSpielID(spielID: SpielelementID.SpielID) =
			"$route/${spielID.toInt()}"
	}
}
