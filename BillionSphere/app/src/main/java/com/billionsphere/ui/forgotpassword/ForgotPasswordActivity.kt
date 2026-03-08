package com.billionsphere.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.otpverification.OtpVerificationActivity
import com.billionsphere.ui.register.RegisterViewModel
import com.billionsphere.utils.AppStrings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : BaseVMComposeActivity<RegisterViewModel>(
    RegisterViewModel::class.java
) {
    @Composable
    override fun Content(vm: RegisterViewModel) {
        return ForgotPasswordScreen(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.dropDownApiResponse.observe(this) {
            it?.data.let {
                val newCountryList = it.orEmpty()
                viewModel._countryList.value = newCountryList
            }
        }
        viewModel.forgotPasswordApiResponse.observe(this){
            viewModel.showSuccess(it?.message, autoHideMs = 2000L, blockUi = true)
            it?.data.let {
                val intent = Intent(this, OtpVerificationActivity::class.java)
                intent.putExtra(AppStrings.IntentData.email,viewModel.loginUiState.value.emailOrPhoneNumber)
                intent.putExtra(AppStrings.IntentData.phoneNumber,viewModel.loginUiState.value.emailOrPhoneNumber)
                intent.putExtra(AppStrings.IntentData.countryCode,viewModel.loginUiState.value.countryCode)
                intent.putExtra(AppStrings.IntentData.userId,it?.user_id.toString())
                intent.putExtra(AppStrings.IntentData.type,viewModel.loginUiState.value.type)
                intent.putExtra(AppStrings.IntentData.from, AppStrings.FromActivity.forgotPasswordScreen)
                startActivity(intent)
            }
        }
    }
}