package com.example.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class CupcakeScreenNavigationTest {

    @get:Rule
    val composTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        // GIVEN
        composTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            CupcakeApp(
                navController = navController
            )
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composTestRule.activity.getString(R.string.back_button)
        composTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigatesToSelectFlavorScreen() {
        // WHEN
        composTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeHost_clickUpButtonOnFlavorScreen_navigatesToStarScreen() {
        // GIVEN
        navigateToFlavorScreen()

        // WHEN
        performerNavigateUp()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeHost_clickCancelButtonOnFlavorScreen_navigatesToStarScreen() {
        // GIVEN
        navigateToFlavorScreen()

        // WHEN
        composTestRule.onNodeWithStringId(R.string.cancel).performClick()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcake_clickNextButtonOnFlavorScreen_navigatesToPickupScreen() {
        // GIVEN
        navigateToFlavorScreen()

        // WHEN
        composTestRule.onNodeWithStringId(R.string.next).performClick()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Test
    fun cupcake_clickUpButtonOnPickupScreen_navigatesToFlavorScreen() {
        // GIVEN
        navigateToPickupScreen()

        // WHEN
        performerNavigateUp()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcake_clickCancelButtonOnPickupScreen_navigateToStartScreen() {
        // GIVEN
        navigateToPickupScreen()

        // WHEN
        composTestRule.onNodeWithStringId(R.string.cancel).performClick()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcake_clickNextButtonOnPickupScreen_navigatesToSummaryScreen() {
        // GIVEN
        navigateToPickupScreen()

        // WHEN
        composTestRule.onNodeWithStringId(R.string.next).performClick()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Summary.name)
    }

    @Test
    fun cupcake_clickUpButtonOnSummaryScreen_navigatesToPickupScreen() {
        // GIVEN
        navigateToSummaryScreen()

        // WHEN
        performerNavigateUp()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Pickup.name)
    }

    @Test
    fun cupcake_clickCancelButtonOnSummaryScreen_navigateToStartScreen() {
        // GIVEN
        navigateToSummaryScreen()

        // WHEN
        composTestRule.onNodeWithStringId(R.string.cancel).performClick()

        // THEN
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    private fun navigateToFlavorScreen() {
        composTestRule.onNodeWithStringId(R.string.one_cupcake).performClick()
        composTestRule.onNodeWithStringId(R.string.chocolate).performClick()
    }

    private fun navigateToPickupScreen() {
        navigateToFlavorScreen()
        composTestRule.onNodeWithStringId(R.string.next).performClick()
        composTestRule.onNodeWithText(getFormattedDate()).performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickupScreen()
        composTestRule.onNodeWithStringId(R.string.next).performClick()
    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    private fun performerNavigateUp() {
        composTestRule.onNodeWithDescriptionId(R.string.back_button).performClick()
    }
}
