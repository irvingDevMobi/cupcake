package com.example.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.ui.SelectOptionScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // GIVEN list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        // WHEN SelectOptionScreen is loaded
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        // THEN all the options are displayed on the screen
        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }
        // And THEN the subtotal is displayed correctly
        val subTotalText = composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        composeTestRule.onNodeWithText(subTotalText).assertIsDisplayed()
        // And THEN the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun selectOption_enablesActionButton() {
        // GIVEN
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()

        // WHEN
        val optionSelected = flavors.random()
        composeTestRule.onNodeWithText(optionSelected).performClick()

        // THEN
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}
