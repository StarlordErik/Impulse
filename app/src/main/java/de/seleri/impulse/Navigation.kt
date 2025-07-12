package de.seleri.impulse

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.finde

@Composable
fun Navigation(dbs: Datenbanksystem) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Start.route) {

        composable(Screen.Start.route) {
            StartScreen(navController, dbs)
        }

        composable(
            route = Screen.Spiel.route + "/{spielID}",
            arguments = listOf(
                navArgument("spielID") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { eingabe ->
            val id = eingabe.arguments!!.getInt("spielID")
            SpielScreen(
                dbs = dbs,
                spiel = dbs.spiele.finde(id)
            )
        }
    }
}

sealed class Screen(val route : String) {
    object Start : Screen("start")
    object Spiel : Screen("spiel")

    fun mitArgumenten(vararg args : Any) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
