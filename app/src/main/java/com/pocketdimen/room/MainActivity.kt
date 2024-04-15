package com.pocketdimen.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pocketdimen.room.database.InventoryDatabase
import com.pocketdimen.room.screen.HomeScreen
import com.pocketdimen.room.screen.InventoryViewModel
import com.pocketdimen.room.screen.ItemEntryScreen
import com.pocketdimen.room.ui.theme.RoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val inventoryViewModel: InventoryViewModel = viewModel(factory = AppViewModelProvider.Factory)
                    val uiState by inventoryViewModel.uiState.collectAsState()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.name
                    ) {


                        composable(Screen.HomeScreen.name) {
                            HomeScreen(items = uiState.items) {
                                navController.navigate(Screen.ItemEntryScreen.name)
                            }
                        }
                        composable(Screen.ItemEntryScreen.name) {
                            ItemEntryScreen(
                                inventoryUiState = uiState ,
                                onItemNameChange ={
                                    inventoryViewModel.onItemNameChange(it)
                                },
                                onItemPriceChange = {
                                                    inventoryViewModel.onItemPriceChange(it)
                                },
                                onItemQuantityChange = {
                                                       inventoryViewModel.onItemQuantityChange(it)
                                },
                                onSaveClick = {
                                    inventoryViewModel.onSaveClicked()
                                    navController.navigateUp()
                                }
                            )
                        }

                    }

                }

            }
        }
    }
}

/*
1. gradle setup
2. database setup: Entity, Dao, Database class
3. Repository
4. appconfig: AppDataContainer, InventoryApplication
5. Update manifest
 */