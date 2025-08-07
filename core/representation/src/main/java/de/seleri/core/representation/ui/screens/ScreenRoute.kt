package de.seleri.core.representation.ui.screens

import de.seleri.core.common.ids.spielelementID.SpielID

sealed class ScreenRoute(val route: String) { object StartscreenRoute: ScreenRoute("start")
	object SpielscreenRoute: ScreenRoute("spiel") {

		fun mitDerSpielID(spielID: SpielID) =
			"$route/${spielID.value}"
	}
}
