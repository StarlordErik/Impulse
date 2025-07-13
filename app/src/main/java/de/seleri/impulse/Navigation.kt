package de.seleri.impulse

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

const val TRANSITION_DAUER = 300

@Composable
fun Navigation(viewModel: ImpulseViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Start.route) {

        composable(Screen.Start.route, enterTransition = {
            when (initialState.destination.route) {
                Screen.Spiel.route + "/{spielID}" -> slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DAUER)
                )

                else -> null
            }
        }, exitTransition = {
            when (targetState.destination.route) {
                Screen.Spiel.route + "/{spielID}" -> slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(TRANSITION_DAUER)
                )

                else -> null
            }
        }, popEnterTransition = {
            when (initialState.destination.route) {
                Screen.Spiel.route + "/{spielID}" -> slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DAUER)
                )

                else -> null
            }
        }, popExitTransition = {
            when (targetState.destination.route) {
                Screen.Spiel.route + "/{spielID}" -> slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(TRANSITION_DAUER)
                )

                else -> null
            }
        }) {
            StartScreen(navController, viewModel)
        }

        composable(
            route = Screen.Spiel.route + "/{spielID}", arguments = listOf(
                navArgument("spielID") {
                    type = NavType.IntType
                    nullable = false
                })
        ) { eingabe ->
            val id = eingabe.arguments!!.getInt("spielID")
            val spiel = viewModel.getSpiel(id)
            SpielScreen(viewModel, spiel)
        }
    }
}

sealed class Screen(val route: String) {
    object Start: Screen("start")
    object Spiel: Screen("spiel")

    fun mitDerID(id: Int): String {
        return buildString {
            append(route)
            append("/$id")
        }
    }
}
