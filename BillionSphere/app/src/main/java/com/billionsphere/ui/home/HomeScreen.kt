package com.billionsphere.ui.home

import android.content.Intent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.billionsphere.R
import com.billionsphere.ui.components.CompactDropdown
import com.billionsphere.ui.components.DefaultBottomSheet
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.LabeledField
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.forgotpassword.ForgotPasswordActivity
import com.billionsphere.ui.theme.*
import com.billionsphere.utils.AppStrings

@Composable
fun HomeScreen(vm: HomeViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.billion_sphere_login_ic),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(dimen_20),
            verticalArrangement = Arrangement.spacedBy(dimen_24),
            horizontalArrangement = Arrangement.spacedBy(dimen_16)
        ) {

            item {
                HomeCard(
                    title = stringResource(R.string.marketiqon),
                    subtitle = stringResource(R.string.digital_advertising_platform),
                    image = R.drawable.digital_marketing_ic,
                    gradient = Brush.verticalGradient(listOf(SkyBluish, Blue72Opacity))
                )
            }

            item {
                HomeCard(
                    title = stringResource(R.string.buyla),
                    subtitle = stringResource(R.string.e_commerce_platform),
                    image = R.drawable.ecommerce_ic,
                    gradient = Brush.verticalGradient(listOf(Green, Greyish)
                ))
            }

            item {
                HomeCard(
                    title = stringResource(R.string.ledgira),
                    subtitle = stringResource(R.string.accounting_software),
                    image = R.drawable.gov_services_ic,
                    gradient = Brush.verticalGradient(listOf(Color.White, LightGreyish)
                ))
            }

            item {
                HomeCard(
                    title = stringResource(R.string.tripora),
                    subtitle = stringResource(R.string.travel_rides_logistics),
                    image = R.drawable.rapido_ic,
                    gradient = Brush.verticalGradient(listOf(Color.Black,Blue47Opacity)))
            }
            item {
                HomeCard(
                    title = stringResource(R.string.foodelt),
                    subtitle = stringResource(R.string.food_delivery_platform),
                    image = R.drawable.zomato_ic,
                    gradient = Brush.verticalGradient(listOf(Yellow,MustardYellow))
                )
            }
            item {
                HomeCard(
                    title = stringResource(R.string.gamorax),
                    subtitle = stringResource(R.string.gaming_platform),
                    image = R.drawable.game_ic,
                    gradient = Brush.verticalGradient(listOf(Red, Violetish))
                )
            }
            item {
                HomeCard(
                    title = stringResource(R.string.stoxenova),
                    subtitle = stringResource(R.string.stock_market_learning),
                    image = R.drawable.stock_market_ic,
                    gradient = Brush.verticalGradient(listOf(SkyBlueGreen, DarkGreyish))
                )
            }
            item {
                HomeCard(
                    title = stringResource(R.string.crealooma),
                    subtitle = stringResource(R.string.creator_social_platform),
                    image = R.drawable.social_platform_ic,
                    gradient = Brush.verticalGradient(listOf(DarkishBlue, Blackish))
                )
            }

        }
    }
}

@Composable
fun HomeCard(
    title: String,
    subtitle: String,
    image: Int,
    gradient: Brush
) {

    Box(
        modifier = Modifier
            .width(dimen_168)
            .wrapContentHeight(),
        contentAlignment = Alignment.TopCenter
    ) {

        // Card
        Box(
            modifier = Modifier
                .padding(top = dimen_50) // space for image overlap
                .height(dimen_122)
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimen_20))
                .background(gradient)
                .padding(dimen_12),
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Textview(
                    text = title,
                    color = Color.White,
                    size = font_16,
                    bold = true
                )

                Spacer(modifier = Modifier.height(dimen_4))

                Textview(
                    text = subtitle,
                    color = Color.White,
                    size = font_10
                )
            }
        }

//        RotatingImage(image)
      Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(dimen_120)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun RotatingImage(image: Int) {

    val infiniteTransition = rememberInfiniteTransition(label = "")

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = Modifier
            .size(dimen_120)
            .graphicsLayer {
                rotationZ = rotation
            }
    )
}