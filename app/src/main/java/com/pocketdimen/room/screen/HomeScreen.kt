package com.pocketdimen.room.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
    onItemClick: (Item) -> Unit,
    onDeleteClick: (Item) -> Unit,
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
                        ItemComponent(
                            item = item,
                            onItemClick = onItemClick,
                            onDeleteClick = onDeleteClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemComponent(
    item: Item,
    onItemClick: (Item) -> Unit,
    onDeleteClick: (Item) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable {
                onItemClick(item)
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp, 16.dp)
        ){
            Column {
                Row {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { onDeleteClick(item) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete inventory"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {

                    Text(text = "price: $${item.price}")
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "quantity: ${item.quantity}")
                }
            }

        }
    }
}