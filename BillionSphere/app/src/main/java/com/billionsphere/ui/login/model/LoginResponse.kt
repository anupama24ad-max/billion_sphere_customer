package com.billionsphere.ui.login.model

data class LoginResponse(
    val email: String?="",
    val id: String?="",
    val mobile: String?="",
    val role_id: Int?=0
)