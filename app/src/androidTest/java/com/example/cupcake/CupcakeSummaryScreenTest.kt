package com.example.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import org.junit.Rule
import org.junit.Test

class CupcakeSummaryScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun verifyOrderContent() {
        // GIVEN
        val quantity = 3
        val flavor = "chocolate"
        val date = "17/09/23"
        val price = "$100.00"
        val order = OrderUiState(quantity, flavor, date, price)

        // WHEN
        composeTestRule.setContent {
            OrderSummaryScreen(orderUiState = order)
        }

        // THEN
        val expectedQuantityLabel = composeTestRule.activity.resources
            .getQuantityString(R.plurals.cupcakes, quantity, quantity)
        composeTestRule.onNodeWithText(expectedQuantityLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(date).assertIsDisplayed()
        val expectedSubtotalLabel = composeTestRule.activity
            .getString(R.string.subtotal_price, price)
        composeTestRule.onNodeWithText(expectedSubtotalLabel).assertIsDisplayed()
    }
}
