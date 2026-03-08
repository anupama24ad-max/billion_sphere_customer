package com.billionsphere.ui.splash

import android.os.Bundle
import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseVMComposeActivity<SplashViewModel>(SplashViewModel::class.java) {
    @Composable
    override fun Content(vm: SplashViewModel) {
        return SplashScreen(vm)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        // 2) Keep the system splash until ViewModel reports it's ready
        splash.setKeepOnScreenCondition {
            // return true to keep splash; false to dismiss
            false
        }

        // 3) (Optional) Exit animation (runs when splash is about to go away)
        splash.setOnExitAnimationListener { provider ->
            // You can animate the splash icon if you like, then:
            provider.remove() // remove immediately when you’re done
        }
    }
}