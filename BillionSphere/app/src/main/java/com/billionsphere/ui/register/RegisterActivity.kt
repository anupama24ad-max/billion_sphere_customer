package com.billionsphere.ui.register

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseVMComposeActivity<RegisterViewModel>(RegisterViewModel::class.java) {
    @Composable
    override fun Content(vm: RegisterViewModel) {
        return RegisterScreen(vm)
    }
}