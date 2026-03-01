package com.billionsphere.ui.congrats

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.forgotpassword.ForgotPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CongratActivity :
    BaseVMComposeActivity<ForgotPasswordViewModel>(ForgotPasswordViewModel::class.java) {
    @Composable
    override fun Content(vm: ForgotPasswordViewModel) {
        return CongratScreen(vm)
    }
}