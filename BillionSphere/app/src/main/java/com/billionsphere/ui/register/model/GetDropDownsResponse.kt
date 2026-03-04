package com.billionsphere.ui.register.model

class GetDropDownsResponse : ArrayList<GetDropDownsResponseItem>()

data class GetDropDownsResponseItem(
    val country_code: String? = "",
    val country_name: String? = "",
    val createdAt: String? = "",
    val currency: String? = "",
    val currency_name: String? = "",
    val currency_symbol: String? = "",
    val dialing_code: String? = "",
    val emoji: String? = "",
    val flag: String? = "",
    val id: Int? = 0,
    val updatedAt: String? = ""
)