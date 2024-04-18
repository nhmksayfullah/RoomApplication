package com.pocketdimen.room.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pocketdimen.room.AppViewModelProvider
import com.pocketdimen.room.R
import com.pocketdimen.room.database.Item
import kotlinx.coroutines.launch
import java.util.Currency
import java.util.Locale

@Composable
fun ItemEntryScreen(
    inventoryUiState: InventoryUiState,
    onItemNameChange: (String) -> Unit,
    onItemPriceChange: (String) -> Unit,
    onItemQuantityChange: (String) -> Unit,
    onSaveClick: () -> Unit
) {



    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ItemEntryBody(
            inventoryUiState = inventoryUiState,
            onItemNameChange = onItemNameChange,
            onItemPriceChange =  onItemPriceChange,
            onItemQuantityChange = onItemQuantityChange,
            onSaveClick = {
                coroutineScope.launch {
                    onSaveClick()
                }
            },
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }

}



@Composable
fun ItemEntryBody(
    inventoryUiState: InventoryUiState,
    onItemNameChange: (String) -> Unit,
    onItemPriceChange: (String) -> Unit,
    onItemQuantityChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        ItemInputForm(
            inventoryUiState = inventoryUiState,
            onItemNameChange = onItemNameChange,
            onItemPriceChange =  onItemPriceChange,
            onItemQuantityChange = onItemQuantityChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (inventoryUiState.currentItemId != -1) "Update" else "Save")
        }
    }
}


@Composable
fun ItemInputForm(
    inventoryUiState: InventoryUiState,
    modifier: Modifier = Modifier,
    onItemNameChange: (String) -> Unit,
    onItemPriceChange: (String) -> Unit,
    onItemQuantityChange: (String) -> Unit,
    enabled: Boolean = true
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        OutlinedTextField(
            value = inventoryUiState.currentItemName,
            onValueChange = {
                onItemNameChange(it)
            },
            label = { Text(stringResource(R.string.item_name_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = inventoryUiState.currentItemPrice,
            onValueChange = {
                onItemPriceChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text(stringResource(R.string.item_price_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            leadingIcon = { Text(Currency.getInstance(Locale.getDefault()).symbol) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = inventoryUiState.currentItemQuantity,
            onValueChange = {
                onItemQuantityChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.quantity_req)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = stringResource(R.string.required_fields),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}