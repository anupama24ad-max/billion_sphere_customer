package com.billionsphere.repository

import com.billionsphere.api.ApiResult
import com.billionsphere.api.Resource
import com.billionsphere.api.RestApi
import com.billionsphere.api.safeApiCall
import com.billionsphere.ui.login.model.LoginResponse
import com.billionsphere.ui.register.model.GetDropDownsResponse
import com.billionsphere.ui.register.model.RegisterResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class Repository @Inject constructor(val api: RestApi) {

    private val TAG = "Repository"

    suspend fun registerApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<RegisterResponse>?> {

        return safeApiCall {
            api.registerApi(
                headers, jsonObj.toString()
                    .toRequestBody("application/json".toMediaTypeOrNull())
            )
        }
    }

    suspend fun dropdownsApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<GetDropDownsResponse>?> {

        return safeApiCall {
            api.dropdownsApi(
                headers, jsonObj.toString()
                    .toRequestBody("application/json".toMediaTypeOrNull())
            )
        }
    }

    suspend fun loginApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<LoginResponse>?> {

        return safeApiCall {
            api.loginApi(
                headers, jsonObj.toString()
                    .toRequestBody("application/json".toMediaTypeOrNull())
            )
        }
    }

    suspend fun forgotPasswordApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<Any>?> {

        return safeApiCall {
            api.forgotPasswordApi(
                headers, jsonObj.toString()
                    .toRequestBody("application/json".toMediaTypeOrNull())
            )
        }
    }
    suspend fun verifyOtpApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<Any>?> {

        return safeApiCall {
            api.verifyOtpApi(
                headers, jsonObj.toString()
                    .toRequestBody("application/json".toMediaTypeOrNull())
            )
        }
    }

    suspend fun resendOtpApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<Any>?> {

        return safeApiCall {
            api.resendOtpApi(
                headers, jsonObj.toString()
                    .toRequestBody("application/json".toMediaTypeOrNull())
            )
        }
    }


}