package com.billionsphere.ui.register.model

data class RegisterResponse(
    val contact_number: String? = "",
    val email: String? = "",
    val id: String? = "",
    val is_approval: Int? = 0,
    val name: String? = "",
    val role_id: Int? = 0
)