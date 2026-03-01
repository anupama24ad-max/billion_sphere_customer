package com.billionsphere.ui.onboarding

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.billionsphere.R
import com.billionsphere.ui.components.GradientButton
import com.billionsphere.ui.components.Textview
import com.billionsphere.ui.login.LoginActivity
import com.billionsphere.ui.register.RegisterActivity
import com.billionsphere.ui.theme.*

@Composable
fun OnboardingScreen(vm: OnboardingViewModel) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.billion_sphere_ic),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(start = dimen_30, end = dimen_30, top = dimen_0, bottom = dimen_165)
        ) {
            GradientButton(
                text = stringResource(R.string.create_an_account),
                size = font_16,
                textColor = Color.White,
            ) {
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            }
            Spacer(modifier = Modifier.height(dimen_10))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Textview(
                    text = stringResource(R.string.already_have_an_account),
                    color = Grey,
                    size = font_12,
                    bold = true,
                    modifier = Modifier.wrapContentSize()
                )
                Textview(
                    text = stringResource(R.string.login),
                    color = Bluish,
                    size = font_12,
                    bold = true,
                    modifier = Modifier.wrapContentSize().clickable{
                        val intent = Intent(context, LoginActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }


        }
    }
}