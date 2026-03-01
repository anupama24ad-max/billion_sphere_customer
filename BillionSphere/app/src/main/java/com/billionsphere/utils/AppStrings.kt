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

        }
    }

    class Constants {
        companion object {

            var authorization = "Authorization"
//            var defaultToken = BuildConfig.API_KEY

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
            var contact = "contact"
            var otp = "otp"
            var reset_password = "reset_password"
            var reset_password_confirmation = "reset_password_confirmation"
            var user_id = "user_id"
            var min = "min"
            var max = "max"
            var search = "search"
            var refresh_token = "refresh_token"
            var work_id = "work_id"
            var assigned_id = "assigned_id"
            var id = "id"
            var category_id = "category_id"
            var user_name = "user_name"
            var phone_number = "phone_number"
            var service = "service"
            var message = "message"
            var page_number = "page_number"
            var page_size = "page_size"
            var other_user_id = "other_user_id"
            var old_password = "old_password"
            var new_password = "new_password"
            var topic_ids = "topic_ids"
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
    class CustomerStatus{
        companion object{
            val pending = 0
            val completed = 1
            val ongoing = 2
        }
    }


}