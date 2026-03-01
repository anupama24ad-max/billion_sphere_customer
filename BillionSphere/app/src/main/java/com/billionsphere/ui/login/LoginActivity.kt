package com.billionsphere.ui.login

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseVMComposeActivity<LoginViewModel>(LoginViewModel::class.java) {
    @Composable
    override fun Content(vm: LoginViewModel) {
        return LoginScreen(vm)
    }
}