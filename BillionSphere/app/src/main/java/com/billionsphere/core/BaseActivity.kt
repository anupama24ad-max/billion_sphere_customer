package com.billionsphere.core

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.billionsphere.R
import com.billionsphere.utils.CLog


open class BaseActivity : AppCompatActivity() {

    private var progressDialog: Dialog? = null

    companion object {
        var context: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        context = this
        enableEdgeToEdge()

    }


  fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            showLoading()
        } else hideLoading()
    }


    private fun showLoading() {
        if (progressDialog == null) {
            progressDialog = Dialog(this, R.style.CustomDialog)
        } else {
            if (progressDialog?.isShowing == false) {
                progressDialog?.show()
            }
            return
        }
        val view = LayoutInflater.from(this).inflate(R.layout.app_loading_dialog, null, false)
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setContentView(view)
        progressDialog?.window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                this,
                R.color.black
            )
        )
        progressDialog?.setCancelable(false)
        progressDialog?.setCanceledOnTouchOutside(false)
        progressDialog?.show()
    }


    private fun hideLoading() {
        progressDialog?.dismiss()
    }



    override fun onDestroy() {
        super.onDestroy()
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog?.dismiss()
        }
    }

    override fun onResume() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        CLog.e("", "onResume API call --> In BaseActivity class")
//        validate()
        super.onResume()
    }
}