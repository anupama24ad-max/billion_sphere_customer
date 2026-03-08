package com.billionsphere.ui.otpverification

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.billionsphere.R
import com.billionsphere.api.ApiResult
import com.billionsphere.api.isRequestCallSuspendSuccess
import com.billionsphere.core.BaseViewModel
import com.billionsphere.repository.Repository
import com.billionsphere.ui.login.model.LoginUiState
import com.billionsphere.ui.otpverification.model.OtpVerificationResponse
import com.billionsphere.ui.register.model.RegisterResponse
import com.billionsphere.ui.register.model.RegisterUiState
import com.billionsphere.ui.resetpassword.model.ResetPasswordUiState
import com.billionsphere.utils.AppMethods
import com.billionsphere.utils.AppStrings
import com.billionsphere.utils.CLog
import com.billionsphere.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class OtpVerificationViewModel @Inject constructor(
    var app: Application,
    var repo: Repository,
    var sm: SessionManager
) : BaseViewModel(app) {

    private val TAG = "OtpVerificationViewModel"

    private val _verifyOtpApiResponse: MutableLiveData<ApiResult<OtpVerificationResponse>?> =
        MutableLiveData()
    val verifyOtpApiResponse: LiveData<ApiResult<OtpVerificationResponse>?> = _verifyOtpApiResponse

    private val _resendOtpApiResponse: MutableLiveData<ApiResult<Any>?> =
        MutableLiveData()
    val resendOtpApiResponse: LiveData<ApiResult<Any>?> = _resendOtpApiResponse

    private val _resetPasswordApiResponse: MutableLiveData<ApiResult<Any>?> =
        MutableLiveData()
    val resetPasswordApiResponse: LiveData<ApiResult<Any>?> = _resetPasswordApiResponse

    var type = MutableLiveData<Int>(0)

    private val _verifyUiState = MutableStateFlow(LoginUiState())
    val verifyUiState = _verifyUiState.asStateFlow()

    fun updateVerifyUiState(update: LoginUiState.() -> LoginUiState) {
        _verifyUiState.value = _verifyUiState.value.update()
    }

    var errorMessage = MutableLiveData<String>("")

    private val _resetPasswordUiState = MutableStateFlow(ResetPasswordUiState())
    val resetPasswordUiState = _resetPasswordUiState.asStateFlow()

    fun updateResetPasswordUiState(update: ResetPasswordUiState.() -> ResetPasswordUiState) {
        _resetPasswordUiState.value = _resetPasswordUiState.value.update()
    }

    val isOtpEnabled: StateFlow<Boolean> =
        verifyUiState
            .map { s ->
                s.otp.length == 6
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val isResetEnabled: StateFlow<Boolean> =
        resetPasswordUiState
            .map { s ->
                listOf(
                    s.password,
                    s.confirmPassword,
                ).all { it.trim().isNotEmpty() }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)


    fun onClickResetPasswordBtn() {
        resetValidation()
    }

    fun resetValidation() {
        if (!AppMethods.isStrongPassword(resetPasswordUiState.value.password.trim())) {
            showError(app.getString(R.string.please_enter_valid_password))
        } else if (resetPasswordUiState.value.password.trim()
                .toString() != resetPasswordUiState.value.confirmPassword.trim().toString()
        ) {
            showError(app.getString(R.string.password_doesn_t_match))
        } else {
            resetPasswordApi()
        }
    }


    fun verifyOtpApi() {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.type, verifyUiState.value.type)
        jsonObj.put(AppStrings.InputData.user_id, verifyUiState.value.userId)
        jsonObj.put(AppStrings.InputData.otp, verifyUiState.value.otp.trim())
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.verifyOtpApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _verifyOtpApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "verifyOtpApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }

    fun resendOtpApi() {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.type, type.value)
        jsonObj.put(AppStrings.InputData.user_id, type.value)
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.resendOtpApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _resendOtpApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "resendOtpApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }

    fun resetPasswordApi() {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.password, resetPasswordUiState.value.password.trim())
        jsonObj.put(AppStrings.InputData.user_id, type.value)
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.resetPasswordApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _resetPasswordApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "resetPasswordApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }


}