package com.sample.neuroidapp.us

import androidx.multidex.MultiDexApplication
import com.neuroid.tracker.NeuroID

class NIDPOCApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        val neuroId = NeuroID.Builder(
            this,
            "key_live_dNoyQAlq8w1c0mCeHKl5Sejl"
        ).build()
        NeuroID.setNeuroIdInstance(neuroId)
        NeuroID.getInstance()?.setEnvironment("LIVE")
        NeuroID.getInstance()?.setSiteId("form_picks709")
        val rnds = (0..10000).random().toString()
        NeuroID.getInstance()?.start()
        NeuroID.getInstance()?.setUserID(rnds)
    }
}