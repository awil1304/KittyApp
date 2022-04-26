package de.awil1304.kittyapp.presentation.kitty_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.awil1304.kittyapp.presentation.KittyListViewModel
import de.awil1304.kittyapp.presentation.kitty_list.components.ErrorSnackbar
import de.awil1304.kittyapp.presentation.kitty_list.components.KittyListItem
import kotlinx.coroutines.launch

@Composable
fun KittyListScreen(
    navController: NavController,
    viewModel: KittyListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.kitty) { kitty ->
                KittyListItem(
                    kitty = kitty,
                )
            }
        }
        if (state.error.isNotBlank()) {
            val snackbarHostState = remember { SnackbarHostState() }
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    message = "Error loading data...",
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite
                )
            }
            ErrorSnackbar(navController = navController, snackbarHostState = snackbarHostState)

//            Text(
//                text = state.error,
//                color = MaterialTheme.colors.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}