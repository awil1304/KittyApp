package de.awil1304.kittyapp.presentation.kitty_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.awil1304.kittyapp.R
import de.awil1304.kittyapp.presentation.KittyListViewModel
import de.awil1304.kittyapp.presentation.kitty_list.components.ErrorSnackbar
import de.awil1304.kittyapp.presentation.kitty_list.components.KittyListItem
import de.awil1304.kittyapp.util.WindowInfo
import de.awil1304.kittyapp.util.rememberWindowInfo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KittyListScreen(
    navController: NavController,
    viewModel: KittyListViewModel = hiltViewModel()
) {
    // for device display size
    val windowInfo = rememberWindowInfo()
    val state = viewModel.state.value

    // box for entire screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {

        // column so items are arranged vertically
        Column {

            // app header bar
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.dark_pink)),
                text = "Kitties ♡",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
            )

            // different presentations for different display sizes
            when (windowInfo.screenWidthInfo) {

                // view for devices with compact screen width
                is WindowInfo.WindowType.Compact -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.kittyList) { kitty ->
                            KittyListItem(
                                kitty = kitty,
                                onClick = {
                                }
                            )
                        }
                    }
                }

                // view for devices with medium screen width
                is WindowInfo.WindowType.Medium -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxHeight(),
                        cells = GridCells.Fixed(2), // 2 items next to each other
                        contentPadding = PaddingValues(
                            start = 7.5.dp,
                            end = 7.5.dp,
                            bottom = 100.dp
                        )
                    ) {
                        items(state.kittyList) { kitty ->
                            KittyListItem(
                                kitty = kitty,
                                onClick = {
                                }
                            )
                        }
                    }
                }

                // view for devices with expanded screen width
                else -> {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxHeight(),
                        cells = GridCells.Fixed(3), // 3 items next to each other (alternative: FlowRow?)
                        contentPadding = PaddingValues(
                            start = 7.5.dp,
                            end = 7.5.dp,
                            bottom = 100.dp
                        )
                    ) {
                        items(state.kittyList) { kitty ->
                            KittyListItem(
                                kitty = kitty,
                                onClick = { //TODO
                                }
                            )
                        }
                    }
                }
            }
        }

        // show snackbar if api request fails
        if (state.error.isNotBlank()) {
            val snackbarHostState = remember { SnackbarHostState() }
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    message = "Error loading data...",
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite //indefinite because only click will start new api request
                )
            }
            ErrorSnackbar(navController = navController, snackbarHostState = snackbarHostState)
        }

        // show circular progress bar while loading items from api
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = colorResource(id = R.color.dark_pink)
            )
        }
    }
}


