package com.billionsphere.ui.login.model

data class LoginUiState(
    var emailOrPhoneNumber: String = "",
    var password: String = "",
    var showMobileField: Boolean = false,
    var countryCode: String = "",
    var isLogin: Boolean = false,
    var countryId: Int = 0,
    var type : Int = 0
)