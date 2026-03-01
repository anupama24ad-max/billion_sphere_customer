package com.billionsphere.utils.objects

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.mutableStateOf

object SessionManagerEvent {

    var type = mutableStateOf(AppDialogType.SESSION_EXPIRED)
    var dialogState = mutableStateOf(false to "")
        private set

    fun show(message: String, typeData: AppDialogType) {
        dialogState.value = true to message
        type.value = typeData
    }

    fun hide() {
        dialogState.value = false to ""
        type.value = AppDialogType.SESSION_EXPIRED
    }
}




