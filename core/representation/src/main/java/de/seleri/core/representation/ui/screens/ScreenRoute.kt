package de.seleri.core.representation.ui.screens

sealed class ScreenRoute(val route: String) { object StartscreenRoute: ScreenRoute("start")
	object SpielscreenRoute: ScreenRoute("spiel") {

		fun mitDerSpielID(spielID: Int) =
			"$route/${spielID.toInt()}" // TODO Spielelement.ID
	}
}
