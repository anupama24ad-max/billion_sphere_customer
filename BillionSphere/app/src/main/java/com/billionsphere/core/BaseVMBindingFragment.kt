package com.billionsphere.core

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding


abstract class BaseVMBindingFragment<T : ViewBinding, VM : BaseViewModel>(private var viewModelClass: Class<VM>) : BaseBindingFragment<T>() {

    lateinit var viewModel: VM
    private lateinit var noNetworkDialog: AlertDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.networkLiveData.collect {
                onNetworkChange(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.networkAlerts.collect { state ->
                if (state == NetworkState.NoConnection || state == NetworkState.SlowConnection) {
                    // Create and show the dialog if it's not already showing
                    if (!::noNetworkDialog.isInitialized || !noNetworkDialog.isShowing) {
                        noNetworkDialog = networkAlertDialog(state)
                        noNetworkDialog.show()
                    }
                } else {
                    // Dismiss the dialog if it is showing and the network state is good
                    if (::noNetworkDialog.isInitialized && noNetworkDialog.isShowing) {
                        noNetworkDialog.dismiss()
                    }
                }
            }
        }
    }

    /**
     * Here network change callback occur
     * @param isConnected whether device is connected with network or not
     */
    open fun onNetworkChange(isConnected: Boolean) {
//        Timber.tag("Fragment Connected:").e(if (isConnected) "On Connect" else "On Disconnect")
    }

    private fun networkAlertDialog(state: NetworkState): AlertDialog {
        val (title, message) = when (state) {
            is NetworkState.NoConnection -> {
                "No Internet Connection" to "Please check your internet connection and try again."
            }
            is NetworkState.SlowConnection -> {
                "Slow Internet Connection" to "Please check your internet connection and try again."
            }
            is NetworkState.GoodConnection -> {
                // If you want to handle good connection differently, you can add that logic here.
                return noNetworkDialog // Return the default dialog or create a no-op dialog.
            }
        }

        return AlertDialog.Builder(requireContext()).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("hhh") { dialog, _ ->
                // Handle retry action
            }
            setNegativeButton("bkjh") { dialog, _ ->
                viewModel.cancelNetworkRequest = true
            }
        }.create()
    }

}







/*
abstract class BaseVMBindingFragment<T : ViewBinding, VM : BaseViewModel>(private var viewModelClass: Class<VM>) : BaseBindingFragment<T>() {

    lateinit var viewModel: VM
    private lateinit var noNetworkDialog: AlertDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass]
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.networkLiveData.collect {
                onNetworkChange(it)
            }
        }

        noNetworkDialog = networkAlertDialog()
        lifecycleScope.launchWhenStarted {
            viewModel.networkAlerts.collect { show->
                if (show) {
                    if (!noNetworkDialog.isShowing && viewModel.cancelNetworkRequest.not()) {
                        noNetworkDialog.show()
                    }
                } else {
                    if (noNetworkDialog.isShowing) {
                        noNetworkDialog.dismiss()
                    }
                }
            }
        }
    }

    */
/**
     * Here network change callback occur
     * @param isConnected whether device is connected with network or not
     *//*

    open fun onNetworkChange(isConnected: Boolean) {
//        Timber.tag("Fragment Connected:").e(if (isConnected) "On Connect" else "On Disconnect")
    }

    private fun networkAlertDialog(): AlertDialog {
        val alert = AlertDialog.Builder(requireContext()).apply {
            setTitle(getString(R.string.network_disconnected))
            setMessage(getString(R.string.please_check_the_network_and_retry))
            setPositiveButton(getString(R.string.retry)) { dialog, i ->

            }
            setNegativeButton(getString(R.string.cancel)) { dialog, i ->
                viewModel.cancelNetworkRequest = true
            }
        }
        return alert.create()
    }

}*/
