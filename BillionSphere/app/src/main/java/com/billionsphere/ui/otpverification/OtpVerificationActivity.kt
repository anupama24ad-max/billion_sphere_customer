package com.billionsphere.ui.otpverification

import android.content.Intent
import android.os.Bundle
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.home.HomeActivity
import com.billionsphere.utils.AppStrings
import com.billionsphere.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OtpVerificationActivity : BaseVMComposeActivity<OtpVerificationViewModel>(
    OtpVerificationViewModel::class.java
) {
    @Composable
    override fun Content(vm: OtpVerificationViewModel) {
        return OtpVerificationScreen(vm)
    }

    @Inject
    lateinit var sm: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        setObservers()
    }

    private fun setObservers() {
        viewModel.verifyOtpApiResponse.observe(this) {
            viewModel.showSuccess(it?.message, autoHideMs = 2000L, blockUi = true)
            it?.data.let {
                if (viewModel.verifyUiState.value.from == AppStrings.Type.phoneNumber) {
                    viewModel.verifyUiState.value.type = AppStrings.Type.email
                    viewModel.verifyUiState.value.from = AppStrings.Type.email
                    viewModel.updateVerifyUiState {
                        copy(
                            type = AppStrings.Type.email,
                            otp = ""
                        )
                    }
                } else if (viewModel.verifyUiState.value.from == AppStrings.Type.email) {
                    sm.setData(AppStrings.SessionValues.userId, it?.id.toString())
                    sm.setData(AppStrings.SessionValues.email, it?.email.toString())
                    sm.setData(AppStrings.SessionValues.firstName, it?.first_name.toString())
                    sm.setData(AppStrings.SessionValues.lastName, it?.last_name.toString())
                    sm.setData(AppStrings.SessionValues.accessToken, it?.access_token.toString())
                    sm.setData(AppStrings.SessionValues.refreshToken, it?.refresh_token.toString())
                    sm.setUserLoggedIn(true)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun getIntentData() {
        viewModel.verifyUiState.value.from = intent.getIntExtra(AppStrings.IntentData.from, 0)
        viewModel.verifyUiState.value.email =
            intent.getStringExtra(AppStrings.IntentData.email).toString()
        viewModel.verifyUiState.value.phoneNumber =
            intent.getStringExtra(AppStrings.IntentData.phoneNumber).toString()
        viewModel.verifyUiState.value.userId =
            intent.getStringExtra(AppStrings.IntentData.userId).toString()
        viewModel.verifyUiState.value.type = intent.getIntExtra(AppStrings.IntentData.type, 0)
        viewModel.verifyUiState.value.countryCode =
            intent.getStringExtra(AppStrings.IntentData.countryCode).toString()
    }
}