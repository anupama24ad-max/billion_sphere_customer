package com.billionsphere.api

import com.billionsphere.ui.login.model.LoginResponse
import com.billionsphere.ui.register.model.GetDropDownsResponse
import com.billionsphere.ui.register.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap


interface RestApi {

    @POST(RESTURLS.register)
    suspend fun registerApi(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: RequestBody
    ): Response<ApiResult<RegisterResponse>>

    @POST(RESTURLS.dropdowns)
    suspend fun dropdownsApi(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: RequestBody
    ): Response<ApiResult<GetDropDownsResponse>>

    @POST(RESTURLS.login)
    suspend fun loginApi(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: RequestBody
    ): Response<ApiResult<LoginResponse>>

    @POST(RESTURLS.forgotPassword)
    suspend fun forgotPasswordApi(
        @HeaderMap headers: Map<String, String>,
        @Body jsonObject: RequestBody
    ): Response<ApiResult<Any>>



}
