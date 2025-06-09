package com.jdev.avanzo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import com.jdev.avanzo.data.remote.model.FoodDetail
import com.jdev.avanzo.presentation.FoodViewModel
import com.jdev.avanzo.presentation.navigation.NavigationScreen
import com.jdev.avanzo.util.toImageBitmapFromBase64
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

        NavHost(
            navController = navController,
            startDestination = NavigationScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            composable(route = NavigationScreen.Start.name) {

                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Avanzo") }, actions = {
                            IconButton(onClick = { }) {
                                Icon(Icons.Default.AccountCircle, contentDescription = null)
                            }
                        })
                    }
                ) { innerPadding ->

                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        OutlinedTextField(
                            value = "",
                            onValueChange = { /*onSearch(it)*/ },
                            leadingIcon = @Composable { Icon(Icons.Default.Search, null) },
                            placeholder = @Composable { Text("Search food...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }

                    /*FoodListItem(
                        item = FoodDetail(
                            title = "FoodTest",
                            description = "Description, which is really really long to make sure it's visible and it correctly ellipses",
                            pickupAddress = "Address",
                            userId = 1,
                            isFree = false,
                            image = "",
                            pickupTime = ""
                        ),
                        onClick = {}
                    )*/

                }

            }
        }

    }
}

@Composable
fun FoodListItem(item: FoodDetail, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            bitmap = item.image.toImageBitmapFromBase64()!!,
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
