package com.sample.neuroidapp.us

import androidx.multidex.MultiDexApplication
import com.neuroid.tracker.NeuroID

class NIDPOCApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        val neuroId = NeuroID.Builder(
            this,
            "key_live_IQMIhq2z33pL0jFRKTlWnZvb"
        ).build()
        NeuroID.setNeuroIdInstance(neuroId)
        NeuroID.getInstance().start()
    }
}