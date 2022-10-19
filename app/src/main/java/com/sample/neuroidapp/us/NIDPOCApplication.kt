package com.sample.neuroidapp.us

import androidx.multidex.MultiDexApplication
import com.neuroid.tracker.NeuroID
import com.sample.neuroidapp.us.service.domain.config.ConfigHelper
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NIDPOCApplication : MultiDexApplication() {

    @Inject
    lateinit var configHelper: ConfigHelper

    override fun onCreate() {
        super.onCreate()

        val neuroId = NeuroID.Builder(
            this,
            "key_live_suj4CX90v0un2k1ufGrbItT5"
        ).build()
        NeuroID.setNeuroIdInstance(neuroId)
        NeuroID.getInstance()?.setEnvironment("LIVE")
        NeuroID.getInstance()?.setSiteId("form_picks709")
        NeuroID.getInstance()?.start()
        NeuroID.getInstance()?.setUserID(configHelper.userId)
    }
}