package com.billionsphere.ui.register

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseVMComposeActivity<RegisterViewModel>(RegisterViewModel::class.java) {
    @Composable
    override fun Content(vm: RegisterViewModel) {
        return RegisterScreen(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dropdownsApi()
    }
}