package com.billionsphere.ui.congrats

import android.app.Activity
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.billionsphere.R
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.login.LoginActivity
import com.billionsphere.ui.onboarding.OnboardingActivity
import com.billionsphere.ui.register.RegisterViewModel
import com.billionsphere.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CongratScreen(vm: RegisterViewModel) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)
        context.startActivity(Intent(context, LoginActivity::class.java))
        (context as? Activity)?.finish()
    }
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.billion_sphere_login_ic),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            contentAlignment = Alignment.Center
        ) {

            Box(
                contentAlignment = Alignment.TopCenter
            ) {

                // Glass Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimen_20)
                        .padding(top = dimen_30)
                        .clip(RoundedCornerShape(dimen_60))
                ) {

                    Image(
                        painter = painterResource(R.drawable.glassy_transparent_login_ic),
                        contentDescription = null,
                        modifier = Modifier.matchParentSize(),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimen_36, vertical = dimen_46),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(120.dp))

                        Textview(
                            text = stringResource(R.string.congratulations),
                            color = DarkBlue,
                            size = font_24,
                            bold = true
                        )

                        Spacer(modifier = Modifier.height(dimen_10))

                        Textview(
                            text = "You have successfully created your account",
                            color = Color.White,
                            size = font_12,
                            medium = true
                        )

                        Spacer(modifier = Modifier.height(dimen_24))

                        GradientButton(
                            text = stringResource(R.string.cnt),
                            size = font_16,
                            textColor = Color.White,
                            medium = true,
                            color1 = Violet,
                            color2 = Maroon,
                            borderColor = Color.Transparent,
                            shape = dimen_16
                        ) {

                        }
                    }
                }

                // Rocket (OVERLAY)
                Image(
                    painter = painterResource(R.drawable.rocket_ic),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimen_220)
                        .offset(y = (-10).dp)
                )
            }
        }
    }
}