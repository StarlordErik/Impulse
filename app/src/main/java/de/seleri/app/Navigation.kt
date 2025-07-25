package de.seleri.app

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.seleri.core.di.hiltViewModels.SpielscreenHVM
import de.seleri.core.di.hiltViewModels.StartscreenHVM
import de.seleri.core.representation.ui.screens.ScreenRoute
import de.seleri.core.representation.ui.screens.Spielscreen
import de.seleri.core.representation.ui.screens.Startscreen

const val TRANSITION_DAUER = 300

@Composable
fun Navigation() {
	val navController = rememberNavController()

	NavHost(navController, startDestination = ScreenRoute.StartscreenRoute.route) {

		composable(ScreenRoute.StartscreenRoute.route, enterTransition = {
			when (initialState.destination.route) {
				ScreenRoute.SpielscreenRoute.route + "/{spielID}" -> slideIntoContainer(
					AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(TRANSITION_DAUER)
				)

				else -> null
			}
		}, exitTransition = {
			when (targetState.destination.route) {
				ScreenRoute.SpielscreenRoute.route + "/{spielID}" -> slideOutOfContainer(
					AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(TRANSITION_DAUER)
				)

				else -> null
			}
		}, popEnterTransition = {
			when (initialState.destination.route) {
				ScreenRoute.SpielscreenRoute.route + "/{spielID}" -> slideIntoContainer(
					AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(TRANSITION_DAUER)
				)

				else -> null
			}
		}, popExitTransition = {
			when (targetState.destination.route) {
				ScreenRoute.SpielscreenRoute.route + "/{spielID}" -> slideOutOfContainer(
					AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(TRANSITION_DAUER)
				)

				else -> null
			}
		}) {
			val startscreenHVM: StartscreenHVM = hiltViewModel()
			Startscreen(
				vm = startscreenHVM,
				onSpielClicked = { spielID -> navController.navigate(ScreenRoute.SpielscreenRoute.mitDerSpielID(spielID)) },
			)
		}

		composable(
			route = ScreenRoute.SpielscreenRoute.route + "/{spielID}", arguments = listOf(
				navArgument("spielID") {
					type = NavType.IntType
					nullable = false
				})) {
			val spielscreenHVM: SpielscreenHVM = hiltViewModel()
			Spielscreen(vm = spielscreenHVM)
		}
	}
}
