package com.billionsphere.ui.resetpassword

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity :
    BaseVMComposeActivity<ResetPasswordViewModel>(ResetPasswordViewModel::class.java) {
    @Composable
    override fun Content(vm: ResetPasswordViewModel) {
        return ResetPasswordScreen(vm)
    }
}