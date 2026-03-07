package com.billionsphere.ui.congrats

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CongratActivity :
    BaseVMComposeActivity<RegisterViewModel>(RegisterViewModel::class.java) {
    @Composable
    override fun Content(vm: RegisterViewModel) {
        return CongratScreen(vm)
    }
}