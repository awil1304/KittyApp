package de.awil1304.kittyapp.presentation.kitty_list.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import de.awil1304.kittyapp.util.Screen


@Composable
fun ErrorSnackbar(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val snackbar = createRef()
        SnackbarHost(
            modifier = Modifier
                .constrainAs(snackbar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 8.dp),
            hostState = snackbarHostState,
            snackbar = {
                Snackbar(
                    action = {
                        TextButton(
                            onClick = {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                navController.navigate(Screen.KittyListScreen.route)
                            }
                        ) {
                            Text(
                                fontWeight = FontWeight.Bold,
                                style = TextStyle(color = Color.Red),
                                text = snackbarHostState.currentSnackbarData?.actionLabel ?: ""
                            )
                        }
                    }
                ) {
                    Text(
                        style = TextStyle(color = Color.White),
                        text = snackbarHostState.currentSnackbarData?.message ?: ""
                    )
                }
            }
        )
    }
}