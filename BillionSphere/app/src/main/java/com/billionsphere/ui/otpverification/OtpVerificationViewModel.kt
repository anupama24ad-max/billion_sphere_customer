package com.billionsphere.ui.otpverification

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.billionsphere.api.ApiResult
import com.billionsphere.api.isRequestCallSuspendSuccess
import com.billionsphere.core.BaseViewModel
import com.billionsphere.repository.Repository
import com.billionsphere.ui.login.model.LoginUiState
import com.billionsphere.ui.register.model.RegisterResponse
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

    private val _verifyOtpApiResponse: MutableLiveData<ApiResult<Any>?> =
        MutableLiveData()
    val verifyOtpApiResponse: LiveData<ApiResult<Any>?> = _verifyOtpApiResponse

    private val _resendOtpApiResponse: MutableLiveData<ApiResult<Any>?> =
        MutableLiveData()
    val resendOtpApiResponse: LiveData<ApiResult<Any>?> = _resendOtpApiResponse

    var type = MutableLiveData<Int>(0)

    private val _verifyUiState = MutableStateFlow(LoginUiState())
    val verifyUiState = _verifyUiState.asStateFlow()

    fun updateVerifyUiState(update: LoginUiState.() -> LoginUiState) {
        _verifyUiState.value = _verifyUiState.value.update()
    }

    var errorMessage = MutableLiveData<String>("")

    val isOtpEnabled: StateFlow<Boolean> =
        verifyUiState
            .map { s ->
                s.otp.length == 6
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)


    fun verifyOtpApi() {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.type, type.value)
        jsonObj.put(AppStrings.InputData.user_id, type.value)
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


}