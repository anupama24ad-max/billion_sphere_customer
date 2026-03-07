package com.billionsphere.ui.resetpassword

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.otpverification.OtpVerificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity :
    BaseVMComposeActivity<OtpVerificationViewModel>(OtpVerificationViewModel::class.java) {
    @Composable
    override fun Content(vm: OtpVerificationViewModel) {
        return ResetPasswordScreen(vm)
    }
}