package com.billionsphere.ui.resetpassword

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.congrats.CongratActivity
import com.billionsphere.ui.otpverification.OtpVerificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity :
    BaseVMComposeActivity<OtpVerificationViewModel>(OtpVerificationViewModel::class.java) {
    @Composable
    override fun Content(vm: OtpVerificationViewModel) {
        return ResetPasswordScreen(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.resetPasswordApiResponse.observe(this) {
            viewModel.showSuccess(it?.message, autoHideMs = 2000L, blockUi = true)
            val intent = Intent(this, CongratActivity::class.java)
            startActivity(intent)
        }
    }
}