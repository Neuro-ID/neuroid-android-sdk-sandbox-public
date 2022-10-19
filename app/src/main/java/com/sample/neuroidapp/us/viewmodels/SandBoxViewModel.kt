package com.sample.neuroidapp.us.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neuroid.tracker.NeuroID
import com.sample.neuroidapp.us.service.network.NIDServices
import com.sample.neuroidapp.us.service.network.NetworkInteractor
import com.sample.neuroidapp.us.service.domain.config.ConfigHelper
import com.sample.neuroidapp.us.service.domain.network.Signal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SandBoxViewModel @Inject constructor(
    private val nidServices: NIDServices,
    private val networkInteractor: NetworkInteractor,
    private val configHelper: ConfigHelper
) : ViewModel() {

    private val _error = MutableStateFlow("")
    val error: StateFlow<String>
        get() = _error

    private val _score: MutableStateFlow<List<Signal>> = MutableStateFlow(emptyList())
    val score: StateFlow<List<Signal>>
        get() = _score

    private var jobScore: Job? = null

    private fun createJobScore(): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(4000L)
                checkScore()
            }
        }
    }

    fun startScoreTask() {
        jobScore?.cancel()
        jobScore = createJobScore()
    }

    fun stopScoreTask() {
        jobScore?.cancel()
        jobScore = null
    }

    fun restartApp() {
        NeuroID.getInstance()?.resetClientId()
        NeuroID.getInstance()?.stop()
        NeuroID.getInstance()?.start()
        stopScoreTask()
        startScoreTask()
    }

    private fun checkScore() {
        viewModelScope.launch {
            val result = networkInteractor.safeApiCall {
                nidServices.getProfile(
                    configHelper.apiKey,
                    configHelper.formId,
                    configHelper.userId
                )
            }
            if (result.isError) {
                _error.value = result.networkError?.rawError ?: "No Error Description"
            } else {
                _score.value = result.requiredResult.profile?.signals ?: emptyList()
            }
        }
    }

}