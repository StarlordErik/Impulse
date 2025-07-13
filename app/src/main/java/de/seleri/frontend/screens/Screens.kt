package de.seleri.frontend.screens

sealed class Screens(val route: String) {
    object Start: Screens("start")
    object Spiel: Screens("spiel")

    fun mitDerID(id: Int): String {
        return buildString {
            append(route)
            append("/$id")
        }
    }
}
