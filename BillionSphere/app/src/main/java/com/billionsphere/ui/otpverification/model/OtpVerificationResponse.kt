package com.billionsphere.ui.otpverification.model

data class OtpVerificationResponse(
    val access_token: String?="",
    val email: String?="",
    val first_name: String?="",
    val id: String?="",
    val last_name: String?="",
    val refresh_token: String?=""
)