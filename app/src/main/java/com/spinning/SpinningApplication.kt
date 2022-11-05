package com.spinning

import android.app.Application
import android.content.Context
import com.spinning.storage.SharedPreferenceManager

/**
 * Created by Chandan on 15/4/21
 * Company: Endue Technologies Pvt. LTD
 * Email: chandanjana@enduetechnologies.com
 */
class SpinningApplication: Application() {
    companion object {
        lateinit var sInstance: SpinningApplication
        lateinit var context: Context
        var sharedPreferenceManager: SharedPreferenceManager? = null

    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        sInstance = this
        sharedPreferenceManager = SharedPreferenceManager.getInstance(applicationContext)
        //ApplockManager.instance?.enableDefaultAppLockIfAvailable(this);
        //ApplockManager.instance?.startWaitThread(sInstance)
    }
}