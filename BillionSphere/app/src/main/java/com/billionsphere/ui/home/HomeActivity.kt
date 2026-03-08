package com.billionsphere.ui.home

import androidx.compose.runtime.Composable
import com.billionsphere.core.composecore.BaseVMComposeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseVMComposeActivity<HomeViewModel>(HomeViewModel::class.java) {
    @Composable
    override fun Content(vm: HomeViewModel) {
        return HomeScreen(vm)
    }
}