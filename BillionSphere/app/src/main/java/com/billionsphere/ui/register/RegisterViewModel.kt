package com.billionsphere.ui.register

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.billionsphere.R
import com.billionsphere.api.ApiResult
import com.billionsphere.api.isRequestCallSuspendSuccess
import com.billionsphere.core.BaseViewModel
import com.billionsphere.repository.Repository
import com.billionsphere.ui.forgotpassword.model.ForgotPasswordResponse
import com.billionsphere.ui.login.model.LoginResponse
import com.billionsphere.ui.login.model.LoginUiState
import com.billionsphere.ui.register.model.GetDropDownsResponse
import com.billionsphere.ui.register.model.GetDropDownsResponseItem
import com.billionsphere.ui.register.model.RegisterResponse
import com.billionsphere.ui.register.model.RegisterUiState
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
class RegisterViewModel @Inject constructor(
    var app: Application,
    var repo: Repository,
    var sm: SessionManager
) :
    BaseViewModel(app) {

    private val TAG = "RegisterViewModel"

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState = _registerUiState.asStateFlow()

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun updateRegisterUiState(update: RegisterUiState.() -> RegisterUiState) {
        _registerUiState.value = _registerUiState.value.update()
    }

    fun updateLoginUiState(update: LoginUiState.() -> LoginUiState) {
        _loginUiState.value = _loginUiState.value.update()
    }

    init {
        dropdownsApi()
    }

    private val _registerApiResponse: MutableLiveData<ApiResult<RegisterResponse>?> =
        MutableLiveData()
    val registerApiResponse: LiveData<ApiResult<RegisterResponse>?> = _registerApiResponse

    private val _dropDownApiResponse: MutableLiveData<ApiResult<GetDropDownsResponse>?> =
        MutableLiveData()
    val dropDownApiResponse: LiveData<ApiResult<GetDropDownsResponse>?> = _dropDownApiResponse

    private val _loginApiResponse: MutableLiveData<ApiResult<LoginResponse>?> =
        MutableLiveData()
    val loginApiResponse: LiveData<ApiResult<LoginResponse>?> = _loginApiResponse

    private val _forgotPasswordApiResponse: MutableLiveData<ApiResult<ForgotPasswordResponse>?> =
        MutableLiveData()
    val forgotPasswordApiResponse: LiveData<ApiResult<ForgotPasswordResponse>?> = _forgotPasswordApiResponse

    val _countryList = MutableStateFlow<List<GetDropDownsResponseItem>?>(emptyList())
    val countryList: StateFlow<List<GetDropDownsResponseItem>?> = _countryList

    var errorMessage = MutableLiveData<String>("")


    val isRegisterEnabled: StateFlow<Boolean> =
        registerUiState
            .map { s ->
                listOf(
                    s.firstName,
                    s.lastName,
                    s.password,
                    s.confirmPassword,
                ).all { it.trim().isNotEmpty() }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)


    fun onClickRegisterBtn(context: Activity? = null) {
        registerValidation(context)
    }

    fun onClickLoginBtn(context: Activity? = null) {
        loginValidation(context)
    }

    fun onClickForgotPasswordBtn() {
        forgotPasswordValidation()
    }

    fun registerValidation(context: Activity?) {
        if (registerUiState.value.mobileNumber.length < 3 || registerUiState.value.mobileNumber.length > 15) {
            showError(app.getString(R.string.mobile_number_length_between_3_to_15))
        } else if (registerUiState.value.emailId.trim().isNotEmpty() && !AppMethods.isValidEmail(
                registerUiState.value.emailId.trim()
            )
        ) {
            showError(app.getString(R.string.please_enter_valid_emailaddress))
        } else if (!AppMethods.isStrongPassword(registerUiState.value.password.trim())) {
            showError(app.getString(R.string.please_enter_valid_password))
        } else if (registerUiState.value.password.trim()
                .toString() != registerUiState.value.confirmPassword.trim().toString()
        ) {
            showError(app.getString(R.string.password_doesn_t_match))
        } else {
            context?.let {
                registerApi(context)
            }
        }
    }

    fun loginValidation(context: Activity?) {
        if (loginUiState.value.type == AppStrings.Type.email && !AppMethods.isValidEmail(
                loginUiState.value.emailOrPhoneNumber.trim()
            )
        ) {
            showError(app.getString(R.string.please_enter_valid_emailaddress))
        } else if (loginUiState.value.type == AppStrings.Type.phoneNumber && (loginUiState.value.emailOrPhoneNumber.length < 3 || loginUiState.value.emailOrPhoneNumber.length > 15)) {
            showError(app.getString(R.string.mobile_number_length_between_3_to_15))
        } else if (!AppMethods.isStrongPassword(loginUiState.value.password.trim())) {
            showError(app.getString(R.string.please_enter_valid_password))
        } else {
            context?.let {
                loginApi(context)
            }
        }
    }

    fun forgotPasswordValidation() {
        if (loginUiState.value.type == AppStrings.Type.email && !AppMethods.isValidEmail(
                loginUiState.value.emailOrPhoneNumber.trim()
            )
        ) {
            showError(app.getString(R.string.please_enter_valid_emailaddress))
        } else if (loginUiState.value.type == AppStrings.Type.phoneNumber && (loginUiState.value.emailOrPhoneNumber.length < 3 || loginUiState.value.emailOrPhoneNumber.length > 15)) {
            showError(app.getString(R.string.mobile_number_length_between_3_to_15))
        } else {
            forgotPasswordApi()
        }
    }


    val isForgotPasswordEnabled: StateFlow<Boolean> = loginUiState.map { s ->
        val fields = mutableListOf(
            s.emailOrPhoneNumber,
        )

        if (s.type == AppStrings.Type.phoneNumber) {
            fields.add(s.countryCode)
        }

        fields.all { it.trim().isNotEmpty() }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    val isLoginEnabled: StateFlow<Boolean> = loginUiState.map { s ->
        val fields = mutableListOf(
            s.emailOrPhoneNumber,
            s.password
        )

        if (s.type == AppStrings.Type.phoneNumber) {
            fields.add(s.countryCode)
        }

        fields.all { it.trim().isNotEmpty() }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)

    fun registerApi(
        context: Context
    ) {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.first_name, registerUiState.value.firstName.trim())
        jsonObj.put(AppStrings.InputData.last_name, registerUiState.value.lastName.trim())
        jsonObj.put(AppStrings.InputData.country_id, registerUiState.value.countryId)
        jsonObj.put(AppStrings.InputData.mobile_number, registerUiState.value.mobileNumber.trim())
        jsonObj.put(AppStrings.InputData.email_id, registerUiState.value.emailId.trim())
        jsonObj.put(AppStrings.InputData.address, registerUiState.value.address.trim())
        jsonObj.put(AppStrings.InputData.city, registerUiState.value.city.trim())
        jsonObj.put(AppStrings.InputData.pin_code, registerUiState.value.pinCode.trim())
        jsonObj.put(AppStrings.InputData.state, registerUiState.value.state.trim())
        jsonObj.put(AppStrings.InputData.multi_role_ids, 1)
        jsonObj.put(AppStrings.InputData.sponser_name, registerUiState.value.sponsorName.trim())
        jsonObj.put(AppStrings.InputData.reference_code, registerUiState.value.referenceCode.trim())
        jsonObj.put(AppStrings.InputData.password, registerUiState.value.password.trim())
        jsonObj.put(AppStrings.InputData.device_id, AppStrings.Constants.deviceId)
        jsonObj.put(AppStrings.InputData.device_unique_id, AppMethods.getAndroidId(context))
        jsonObj.put(AppStrings.InputData.platform, AppStrings.Constants.android)
        jsonObj.put(AppStrings.InputData.device_details, AppMethods.getDeviceDetails())
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.registerApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _registerApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "registerApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }

    fun dropdownsApi(
    ) {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.type, AppStrings.DropDownType.country)
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.dropdownsApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _dropDownApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "dropdownsApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }

    fun loginApi(
        context: Context
    ) {
        val jsonObj = JSONObject()
        jsonObj.put(
            AppStrings.InputData.user_identifier,
            loginUiState.value.emailOrPhoneNumber.trim()
        )
        jsonObj.put(AppStrings.InputData.type, loginUiState.value.type)
        jsonObj.put(AppStrings.InputData.password, loginUiState.value.password.trim())
        if (loginUiState.value.type == AppStrings.Type.phoneNumber) {
            jsonObj.put(AppStrings.InputData.country_id, loginUiState.value.countryId)
        }
        jsonObj.put(AppStrings.InputData.multi_role_ids, 1)
        jsonObj.put(AppStrings.InputData.device_id, AppStrings.Constants.deviceId)
        jsonObj.put(AppStrings.InputData.device_unique_id, AppMethods.getAndroidId(context))
        jsonObj.put(AppStrings.InputData.platform, AppStrings.Constants.android)
        jsonObj.put(AppStrings.InputData.device_details, AppMethods.getDeviceDetails())
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.loginApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _loginApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "loginApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }

    fun forgotPasswordApi() {
        val jsonObj = JSONObject()
        jsonObj.put(AppStrings.InputData.type, loginUiState.value.type)
        if (loginUiState.value.type == AppStrings.Type.phoneNumber) {
            jsonObj.put(AppStrings.InputData.country_id, loginUiState.value.countryId)
            jsonObj.put(
                AppStrings.InputData.mobile_number,
                loginUiState.value.emailOrPhoneNumber.trim()
            )
        } else if (loginUiState.value.type == AppStrings.Type.email) {
            jsonObj.put(
                AppStrings.InputData.email_id,
                loginUiState.value.emailOrPhoneNumber.trim()
            )
        }
        viewModelScope.launch {
            runWhenOnline {
                setIsLoading(true)
                repo.forgotPasswordApi(AppMethods.getToken(sm, true), jsonObj)
                    .isRequestCallSuspendSuccess(
                        success = {
                            CLog.e(TAG, it.toString())
                            _forgotPasswordApiResponse.value = it
                        },
                        failure = { body, errorType, message ->
                            Log.e(TAG, "forgotPasswordApi: message --> ${message}")
                            errorMessage.value = message.toString()
                            showError(message.toString())
                        })
                setIsLoading(false)
            }
        }
    }

}