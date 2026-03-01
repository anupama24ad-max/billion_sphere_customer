package com.billionsphere.app


import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.billionsphere.utils.AppStrings
import com.billionsphere.utils.SessionManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BillionSphere : Application(), Application.ActivityLifecycleCallbacks {

    lateinit var sm: SessionManager

    companion object {
        lateinit var instance: BillionSphere
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
//        FirebaseApp.initializeApp(this)
        instance = this
        sm = SessionManager(this)

        val userId = sm.getData(AppStrings.SessionValues.userId, "")
        //  val channel = sm.getData(AppStrings.SessionValues.tripChannel, "")
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {}
}
