package com.billionsphere.ui.onboarding

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity :
    BaseVMComposeActivity<OnboardingViewModel>(OnboardingViewModel::class.java) {
    @Composable
    override fun Content(vm: OnboardingViewModel) {
        return OnboardingScreen(vm)
    }
}