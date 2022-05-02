package com.sample.neuroidapp.us

import androidx.multidex.MultiDexApplication
import com.neuroid.tracker.NeuroID

class NIDPOCApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        val neuroId = NeuroID.Builder(
            this,
            "key_test_7djX5KUDm0onGwb81uZ9SieF"
        ).build()
        NeuroID.setNeuroIdInstance(neuroId)
        NeuroID.getInstance().start()
    }
}