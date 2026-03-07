package com.billionsphere.ui.register.model

data class RegisterUiState (
    var firstName : String = "",
    var lastName : String = "",
    var countryId : Int = 0,
    var mobileNumber : String = "",
    var emailId : String = "",
    var address : String = "",
    var city : String = "",
    var pinCode : String = "",
    var state : String = "",
    var password : String = "",
    var confirmPassword : String = "",
    var sponsorName : String = "",
    var referenceCode : String = "",
    var isRegister : Boolean = false,
    var countryCode : String = ""
    )