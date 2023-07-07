package com.example.cupcake

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeStartScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun verifyOptionsInScreen() {
        // GIVEN
        val options = DataSource.quantityOptions

        // WHEN
        composeTestRule.setContent {
            StartOrderScreen(quantityOptions = options)
        }

        // THEN
        options.forEach { (stringResourceId, _) ->
            composeTestRule.onNodeWithStringId(stringResourceId).assertIsDisplayed()
        }
    }
}
