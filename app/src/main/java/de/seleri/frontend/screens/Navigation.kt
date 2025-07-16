package de.seleri.frontend.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.seleri.viewModel.ImpulseViewModel

/**
 * Dauer der Transition zwischen den [Screens] in Millisekunden
 */
const val TRANSITION_DAUER = 300

/**
 * Navigation über die [Screens]
 *
 * @param viewModel [ImpulseViewModel] mit allen Daten und Funktionen der App
 */
@Composable
fun Navigation(viewModel: ImpulseViewModel = hiltViewModel()) {
  val navController = rememberNavController()

  NavHost(navController, startDestination = Screens.StartScreen.route) {

    composable(Screens.StartScreen.route, enterTransition = {
      when (initialState.destination.route) {
        Screens.SpielScreen.route + "/{spielID}" -> slideIntoContainer(
          AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(TRANSITION_DAUER)
        )

        else -> null
      }
    }, exitTransition = {
      when (targetState.destination.route) {
        Screens.SpielScreen.route + "/{spielID}" -> slideOutOfContainer(
          AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(TRANSITION_DAUER)
        )

        else -> null
      }
    }, popEnterTransition = {
      when (initialState.destination.route) {
        Screens.SpielScreen.route + "/{spielID}" -> slideIntoContainer(
          AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(TRANSITION_DAUER)
        )

        else -> null
      }
    }, popExitTransition = {
      when (targetState.destination.route) {
        Screens.SpielScreen.route + "/{spielID}" -> slideOutOfContainer(
          AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(TRANSITION_DAUER)
        )

        else -> null
      }
    }) {
      StartScreen(navController, viewModel)
    }

    composable(
      route = Screens.SpielScreen.route + "/{spielID}", arguments = listOf(
        navArgument("spielID") {
          type = NavType.IntType
          nullable = false
        })) {eingabe ->
      val id = eingabe.arguments!!.getInt("spielID")
      val spiel = viewModel.getSpiel(id)
      SpielScreen(viewModel, spiel)
    }
  }
}
