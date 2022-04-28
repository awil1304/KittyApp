package de.awil1304.kittyapp.presentation.kitty_list.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.awil1304.kittyapp.R
import de.awil1304.kittyapp.domain.model.Kitty


@Composable
fun KittyListItem(
    // needs kitty object to load into item
    kitty: Kitty,
    // get kitty.id from kitty object in card, so i can indexOf(kitty.id) and then remove and add it at pos0?
    // onClick: () -> Unit,
) {

    var isVisible by remember { mutableStateOf(true) }


    // box for placement in activity
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
    ) {

        // animation if card gets pushed to top position
        AnimatedVisibility(
            visible = isVisible,
            exit = shrinkVertically (animationSpec = tween(1500))
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(), //fixed width so every image is has width as base for scaling height
                shape = RoundedCornerShape(15.dp),
                elevation = 5.dp
            ) {

                // box for image, title and order button
                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.light_pink))
                        .wrapContentHeight() // height adjusted according to image fixed width of parent box
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {

                    // column to arrange image above title
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(5.dp)),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(kitty.imageUrl)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.ic_placeholder),
                            error = painterResource(R.drawable.ic_error),
                            contentDescription = kitty.id,
                            contentScale = ContentScale.FillWidth,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // row to arrange title and order button next to each other
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {

                            // title text box
                            Text(
                                textAlign = TextAlign.Center,
                                text = kitty.title,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )

                            // button to push card to top of list and thus screen
                            IconButton(
                                onClick = { isVisible = !isVisible }) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "Move image to top",
                                    tint = colorResource(id = R.color.dark_pink)
                                )
                            }
                        }
                    }
                }
            }
        }
        // actual card item


    }
}
