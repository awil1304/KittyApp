package de.awil1304.kittyapp.presentation.kitty_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.awil1304.kittyapp.presentation.KittyListViewModel
import de.awil1304.kittyapp.presentation.kitty_list.components.ErrorSnackbar
import de.awil1304.kittyapp.presentation.kitty_list.components.KittyListItemCompact
import de.awil1304.kittyapp.presentation.kitty_list.components.KittyListItemElse
import de.awil1304.kittyapp.util.WindowInfo
import de.awil1304.kittyapp.util.rememberWindowInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KittyListScreen(
    navController: NavController,
    viewModel: KittyListViewModel = hiltViewModel()
) {

    val windowInfo = rememberWindowInfo()
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
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

        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray),
                text = "Kitties",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = Color.White,
            )

            when (windowInfo.screenWidthInfo) {
                is WindowInfo.WindowType.Compact -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.kittyList) { kitty ->
                            KittyListItemCompact(
                                kitty = kitty,
                                onItemClick = {
                                }
                            )
                        }
                    }
                }
                is WindowInfo.WindowType.Medium -> {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(
                            start = 7.5.dp,
                            end = 7.5.dp,
                            bottom = 100.dp
                        ),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(state.kittyList) { kitty ->
                            KittyListItemElse(
                                kitty = kitty,
                                onItemClick = {
                                }
                            )
                        }
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(3),
                        contentPadding = PaddingValues(
                            start = 7.5.dp,
                            end = 7.5.dp,
                            bottom = 100.dp
                        ),
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        items(state.kittyList) { kitty ->
                            KittyListItemElse(
                                kitty = kitty,
                                onItemClick = {
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


