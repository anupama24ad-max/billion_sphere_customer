package com.billionsphere.ui.forgotpassword

import android.app.Application
import com.billionsphere.core.BaseViewModel
import com.billionsphere.repository.Repository
import com.billionsphere.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    var app: Application,
    var sm: SessionManager,
    var repo: Repository
) : BaseViewModel(app) {
}