package com.billionsphere.ui.register

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseVMComposeActivity<RegisterViewModel>(RegisterViewModel::class.java) {

    private val TAG = "RegisterActivity"
    @Composable
    override fun Content(vm: RegisterViewModel) {
        return RegisterScreen(vm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiCalls()
        setObservers()
    }

    private fun setObservers() {
        viewModel.dropDownApiResponse.observe(this){
            it?.data.let {
                val newCountryList = it.orEmpty()
                viewModel._countryList.value = newCountryList
                Log.e(TAG, "setObservers: ${viewModel._countryList.value?.size}", )
            }
        }
    }

    private fun apiCalls() {
        viewModel.dropdownsApi()
    }
}