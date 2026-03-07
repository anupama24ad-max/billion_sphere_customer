package com.billionsphere.ui.otpverification

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerificationActivity : BaseVMComposeActivity<OtpVerificationViewModel>(
    OtpVerificationViewModel::class.java
) {
    @Composable
    override fun Content(vm: OtpVerificationViewModel) {
        return OtpVerificationScreen(vm)
    }
}