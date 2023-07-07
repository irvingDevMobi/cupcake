package com.example.cupcake

import androidx.navigation.NavController
import junit.framework.TestCase.assertEquals

fun NavController.assertCurrentRouteName(expectedRoutedName: String) {
    assertEquals(expectedRoutedName, currentBackStackEntry?.destination?.route)
}

