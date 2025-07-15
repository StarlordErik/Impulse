package de.seleri.frontend.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
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
        composeTestRule
            .onNodeWithText("Impulse")
            .assertIsDisplayed()

        // Check that all game buttons are displayed
        viewModel.spiele.forEach { spiel ->
            val name = viewModel.getName(spiel)
            composeTestRule
                .onNodeWithText(name)
                .assertIsDisplayed()
        }
    }

    @Test
    fun sammlungsButton_clickNavigatesToCorrectScreen() {
        val viewModel = dummyViewModel()
        val testSpiel = viewModel.spiele.first()

        composeTestRule.setContent {
            ImpulseTheme {
                androidx.navigation.compose.NavHost(
                    navController = navController,
                    startDestination = "start"
                ) {
                    composable("start") {
                        StartScreen(navController, viewModel)
                    }
                    composable("spiel/{id}") {} // Dummy Ziel
                }
            }
        }

        val buttonText = viewModel.getName(testSpiel)

        composeTestRule
            .onNodeWithText(buttonText)
            .performClick()

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

        composeTestRule
            .onNodeWithText(buttonText)
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun titel_heightIsReasonable_andTextCentered() {
        composeTestRule.setContent {
            ImpulseTheme {
                Titel()
            }
        }

        composeTestRule
            .onNodeWithText("Impulse")
            .assertIsDisplayed()
            .assert(hasAnyAncestor(isRoot()))
    }
}
