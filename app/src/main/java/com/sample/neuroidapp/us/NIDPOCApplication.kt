package com.sample.neuroidapp.us

import androidx.multidex.MultiDexApplication
import com.neuroid.tracker.NeuroID

class NIDPOCApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        val neuroId = NeuroID.Builder(
            this,
            "key_live_suj4CX90v0un2k1ufGrbItT5"
        ).build()
        NeuroID.setNeuroIdInstance(neuroId)
        NeuroID.getInstance().start()
        val rnds = (0..10000).random().toString()
        NeuroID.getInstance().setUserID(rnds)

    }
}