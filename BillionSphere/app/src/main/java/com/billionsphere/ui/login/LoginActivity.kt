package com.billionsphere.ui.login

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import com.billionsphere.ui.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.orEmpty

@AndroidEntryPoint
class LoginActivity : BaseVMComposeActivity<RegisterViewModel>(RegisterViewModel::class.java) {
    @Composable
    override fun Content(vm: RegisterViewModel) {
        return LoginScreen(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObservers()
    }

    private fun setObservers() {
        viewModel.dropDownApiResponse.observe(this) {
            it?.data.let {
                val newCountryList = it.orEmpty()
                viewModel._countryList.value = newCountryList
            }
        }
        viewModel.loginApiResponse.observe(this){
            it?.data.let {

            }
        }
    }
}