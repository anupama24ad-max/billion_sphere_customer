package com.billionsphere.ui.register

import android.app.Application
import com.billionsphere.core.BaseViewModel
import com.billionsphere.repository.Repository
import com.billionsphere.utils.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    var app: Application,
    var repo: Repository,
    var sm: SessionManager
) :
    BaseViewModel(app) {
}