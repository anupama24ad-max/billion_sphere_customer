package com.billionsphere.ui.splash

import android.app.Activity
import android.content.Intent
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.billionsphere.R
import com.billionsphere.ui.onboarding.OnboardingActivity
import com.billionsphere.ui.theme.dimen_50
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(vm: SplashViewModel) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(2000)
        context.startActivity(Intent(context, OnboardingActivity::class.java))
        (context as? Activity)?.finish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Star Pattern (Optional)
        Image(
            painter = painterResource(id = R.drawable.billion_sphere_auth_bg_ic),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.billion_sphere_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().align(Alignment.Center).padding(horizontal = dimen_50),
            contentScale = ContentScale.Crop
        )

    }
}