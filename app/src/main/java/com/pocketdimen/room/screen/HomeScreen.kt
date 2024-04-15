package com.pocketdimen.room.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pocketdimen.room.database.Item

@Composable
fun HomeScreen(
    items: List<Item>,
    floatingActionButtonClicked: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = floatingActionButtonClicked) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add new item")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            if (items.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Inventory is empty")
                }
            } else {
                LazyColumn {
                    items(items) { item: Item ->
                        ItemComponent(item = item)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemComponent(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Row{
            Text(text = item.name)
            Text(text = item.price.toString())
            Text(text = item.quantity.toString())

        }
    }
}