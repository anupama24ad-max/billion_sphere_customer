package com.billionsphere.repository

import android.util.Log
import com.billionsphere.api.ApiResult
import com.billionsphere.api.Resource
import com.billionsphere.api.RestApi
import com.billionsphere.api.safeApiCall
import com.billionsphere.ui.register.model.GetDropDownsResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class Repository @Inject constructor(val api: RestApi) {

    private val TAG = "Repository"

    suspend fun registerApi(
        headers: Map<String, String>, jsonObj: JSONObject,
    ): Resource<ApiResult<Any>?> {

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


}