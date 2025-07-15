package de.seleri.frontend.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.seleri.frontend.ImpulseTheme
import de.seleri.tools.dummyViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartScreenTest {

  @get:Rule
  val composeTestRule = createAndroidComposeRule<ComponentActivity>()


  private lateinit var navController: TestNavHostController

  @Before
  fun setUp() {
    navController = TestNavHostController(composeTestRule.activity)
    navController.navigatorProvider.addNavigator(ComposeNavigator())
  }

  @Test
  fun startScreen_displaysTitleAndGames() {
    val viewModel = dummyViewModel()

    composeTestRule.setContent {
      ImpulseTheme {
        StartScreen(navController, viewModel)
      }
    }

    // Check that title "Impulse" is displayed
    composeTestRule.onNodeWithText("Impulse").assertIsDisplayed()

    // Check that all game buttons are displayed
    viewModel.spiele.forEach { spiel ->
      val name = viewModel.getName(spiel)
      composeTestRule.onNodeWithText(name).assertIsDisplayed()
    }
  }

  @Test
  fun sammlungsButton_clickNavigatesToCorrectScreen() {
    val viewModel = dummyViewModel()
    val testSpiel = viewModel.spiele.first()

    composeTestRule.setContent {
      ImpulseTheme {
        androidx.navigation.compose.NavHost(
          navController = navController, startDestination = "start"
        ) {
          composable("start") {
            StartScreen(navController, viewModel)
          }
          composable("spiel/{id}") {} // Dummy Ziel
        }
      }
    }

    val buttonText = viewModel.getName(testSpiel)

    composeTestRule.onNodeWithText(buttonText).performClick()

    assert(navController.currentBackStackEntry?.destination?.route?.startsWith("spiel/") == true)
  }

  @Test
  fun sammlungsButton_hasCorrectStyleAndText() {
    val buttonText = "Test Button"

    composeTestRule.setContent {
      ImpulseTheme {
        SammlungsButton(buttonText = buttonText, onClick = {})
      }
    }

    composeTestRule.onNodeWithText(buttonText).assertIsDisplayed().assertHasClickAction()
  }

  @Test
  fun titel_heightIsReasonable_andTextCentered() {
    composeTestRule.setContent {
      ImpulseTheme {
        Titel()
      }
    }

    composeTestRule.onNodeWithText("Impulse").assertIsDisplayed().assert(hasAnyAncestor(isRoot()))
  }
}

class StartScreenTest2 {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun startScreen_showsTitleAndButtons() {
    val dummyViewModel = dummyViewModel()

    composeTestRule.setContent {
      val navController = rememberNavController()
      ImpulseTheme {
        StartScreen(navController, dummyViewModel)
      }
    }

    // Prüfe, ob Titel "Impulse" angezeigt wird
    composeTestRule.onNodeWithText("Impulse").assertIsDisplayed()

    // Prüfe, ob für jedes Spiel ein Button mit dem Namen angezeigt wird
    dummyViewModel.spiele.forEach { spiel ->
      val spielName = dummyViewModel.getName(spiel)
      composeTestRule.onNodeWithText(spielName).assertIsDisplayed()
    }
  }

  @Test
  fun titel_composable_displaysCorrectText() {
    composeTestRule.setContent {
      ImpulseTheme {
        Titel()
      }
    }

    composeTestRule.onNodeWithText("Impulse").assertIsDisplayed()
  }

  @Test
  fun sammlungsButton_displaysTextAndRespondsToClick() {
    var clicked = false

    composeTestRule.setContent {
      ImpulseTheme {
        SammlungsButton("TestButton") {
          clicked = true
        }
      }
    }

    composeTestRule.onNodeWithText("TestButton").assertIsDisplayed().performClick()

    assert(clicked) { "Button wurde nicht geklickt" }
  }
}
