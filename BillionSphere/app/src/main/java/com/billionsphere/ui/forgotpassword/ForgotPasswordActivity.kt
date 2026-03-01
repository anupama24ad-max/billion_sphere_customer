package com.billionsphere.ui.forgotpassword

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : BaseVMComposeActivity<ForgotPasswordViewModel>(
    ForgotPasswordViewModel::class.java
) {
    @Composable
    override fun Content(vm: ForgotPasswordViewModel) {
        return ForgotPasswordScreen(vm)
    }
}