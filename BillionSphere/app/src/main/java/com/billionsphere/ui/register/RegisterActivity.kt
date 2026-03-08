package com.billionsphere.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.otpverification.OtpVerificationActivity
import com.billionsphere.utils.AppStrings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseVMComposeActivity<RegisterViewModel>(RegisterViewModel::class.java) {

    private val TAG = "RegisterActivity"

    @Composable
    override fun Content(vm: RegisterViewModel) {
        return RegisterScreen(vm)
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
        viewModel.registerApiResponse.observe(this) {
            it?.data.let {
                val intent = Intent(this, OtpVerificationActivity::class.java)
                intent.putExtra(AppStrings.IntentData.from, AppStrings.Type.phoneNumber)
                intent.putExtra(AppStrings.IntentData.email,it?.email.toString())
                intent.putExtra(AppStrings.IntentData.phoneNumber,it?.contact_number.toString())
                intent.putExtra(AppStrings.IntentData.countryCode,viewModel.registerUiState.value.countryCode)
                intent.putExtra(AppStrings.IntentData.userId,it?.id.toString())
                intent.putExtra(AppStrings.IntentData.type,AppStrings.Type.phoneNumber)
                intent.putExtra(AppStrings.IntentData.countryCode,viewModel.registerUiState.value.countryCode )
                startActivity(intent)
            }
        }
    }

}