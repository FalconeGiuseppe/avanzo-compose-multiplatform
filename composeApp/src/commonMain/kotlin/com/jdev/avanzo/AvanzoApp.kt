package com.jdev.avanzo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jdev.avanzo.presentation.FoodViewModel
import com.jdev.avanzo.presentation.model.FoodUiModel
import com.jdev.avanzo.presentation.navigation.NavigationScreen
import com.jdev.avanzo.presentation.theme.AppTheme
import com.jdev.avanzo.util.toByteArray
import com.jdev.avanzo.util.toImageBitmapFromBase64
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun AvanzoApp(
    foodViewModel: FoodViewModel = koinViewModel<FoodViewModel>(),
    navController: NavHostController = rememberNavController()
) {

    AppTheme {
        LaunchedEffect(Unit) {
            foodViewModel.getFoods()
        }

        // Get current back stack entry
        val backStackEntry by navController.currentBackStackEntryAsState()

        val currentScreen = NavigationScreen.valueOf(
            value = backStackEntry?.destination?.route ?: NavigationScreen.Start.name
        )

        NavHost(
            navController = navController,
            startDestination = NavigationScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = NavigationScreen.Start.name) {

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Avanzo") }, actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        modifier = Modifier.size(35.dp),
                                        imageVector = Icons.Default.ManageAccounts,
                                        contentDescription = null
                                    )
                                }
                            })
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {},
                        ) {
                            Icon(Icons.Default.Add, null)
                        }
                    }
                ) { innerPadding ->

                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        OutlinedTextField(
                            value = "",
                            onValueChange = { /*onSearch(it)*/ },
                            leadingIcon = { Icon(Icons.Default.Search, null) },
                            placeholder = { Text("Search food...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                        FoodListItem(
                            item = FoodUiModel(
                                id = 0,
                                title = "FoodTest",
                                description = "Description, which is really really long to make sure it's visible and it correctly ellipses",
                                pickupAddress = "Address",
                                userId = 1,
                                isFree = false,
                                image = "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".trim()
                                    .substringAfter(",")
                                    .replace("\n", "")
                                    .replace("\r", "").toByteArray()
                                    .toImageBitmapFromBase64()!!,
                                pickupTime = "",
                                status = ""
                            ),
                            onClick = {}
                        )
                    }

                }

            }
        }
    }
}

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun FoodListItem(item: FoodUiModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            bitmap = item.image,
            contentDescription = item.title,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = item.pickupAddress, style = MaterialTheme.typography.bodySmall)
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
        }

        Icon(Icons.Default.ChevronRight, contentDescription = "Go", tint = Color.Gray)
    }
}
