package com.jdev.avanzo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jdev.avanzo.presentation.FoodViewModel
import com.jdev.avanzo.presentation.navigation.NavigationScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun AvanzoApp(
    foodViewModel: FoodViewModel = koinViewModel<FoodViewModel>(),
    navController: NavHostController = rememberNavController()
) {
    MaterialTheme {
        // Get current back stack entry
        val backStackEntry by navController.currentBackStackEntryAsState()

        val currentScreen = NavigationScreen.valueOf(
            value = backStackEntry?.destination?.route ?: NavigationScreen.Start.name
        )

        Scaffold { innerPadding ->
            val uiState by foodViewModel.uiState.collectAsState()

            NavHost(
                navController = navController,
                startDestination = NavigationScreen.Start.name,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                composable(route = NavigationScreen.Start.name) {

                }
            }
        }

    }
}