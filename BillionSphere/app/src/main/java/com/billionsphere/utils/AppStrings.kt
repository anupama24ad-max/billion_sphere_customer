package com.billionsphere.utils

import com.billionsphere.BuildConfig


class AppStrings {
    class SessionValues {
        companion object {
            var userId = "userId"
            var accessToken = "accessToken"
            var refreshToken = "refreshToken"
            var userName = "userName"
            var name = "name"
            var email = "email"
            var contactNumber = "contactNumber"
            var customerId = "customerId"
            var accountStatus = "accountStatus"
            var firstName = "firstName"
            var lastName = "lastName"

        }
    }

    class Constants {
        companion object {

            var authorization = "Authorization"
            var defaultToken = BuildConfig.API_KEY

            val android = "1"
            val deviceId = "dNDZvU4NQSi2w2sNXPgrEt:APA91bFEnTSViQCaK5y9UyrShyQ4p8-oB_LiKPzGfL-yRAVhvouPHgtCpelZ424SiGVBhrSn71qOebj82BhPLzjG6EQl-jfNkwb_5c_gBhGx1Mrm5mUMTYo"


        }
    }

    class InputData{
        companion object{
            var user_identifier = "user_identifier"
            var password = "password"
            var device_id = "device_id"
            var device_unique_id = "device_unique_id"
            var platform_type = "platform_type"
            var device_details = "device_details"
            var name = "name"
            var contact_number = "contact_number"
            var password_confirmation = "password_confirmation"
            var user_email = "user_email"
            var type = "type"
            var message = "message"
            var first_name = "first_name"
            var last_name = "last_name"
            var country_id = "country_id"
            var mobile_number = "mobile_number"
            var email_id = "email_id"
            var address = "address"
            var city = "city"
            var pin_code = "pin_code"
            var state = "state"
            var multi_role_ids = "multi_role_ids"
            var sponser_name = "sponser_name"
            var reference_code = "reference_code"
            var platform = "platform"
            var user_id = "user_id"
            var otp = "otp"


        }
    }
    class AccountStatus{
        companion object{
            var approved = 0
            var pending = 1
            var resetPassword = 2
            var registration = 4
        }
    }
    class IntentData {
        companion object{
            var successScreen = "successScreen"
            var emailOrPhoneNumber = "emailOrPhoneNumber"
            var type = "type"
            var from = "from"
            var webUrl = "webUrl"
            var webTitle = "webTitle"
            var workId = "workId"
            var assignedId = "assignedId"
            var categoryId = "categoryId"
            var serviceId = "serviceId"
            var particulars = "particulars"
            var email = "email"
            var phoneNumber = "phoneNumber"
            var countryCode = "countryCode"
            var userId = "userId"
        }
    }
    class Type{
        companion object{
            var email = 1
            var phoneNumber = 2
        }
    }
    class FromAcivity{
        companion object{
            var registrationScreen  = "registrationScreen"
            var logoutScreen  = "logoutScreen"
            var deleteScreen  = "deleteScreen"
        }
    }
    class ResponseData {
        companion object {
            val access_token = "access_token"
            val refresh_token = "refresh_token"
            val data = "data"

        }
    }
    class DropDownType{
        companion object{
            val country = 1
            val role = 2
            val nav = 3
        }
    }
    class FromActivity{
        companion object{
            val forgotPasswordScreen = "forgotPasswordScreen"
        }
    }




}