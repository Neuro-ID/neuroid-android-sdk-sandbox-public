package com.sample.neuroidapp.us

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.sample.neuroidapp.us.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import com.sample.neuroidapp.us.listeners.NIDRegisterListener

class MainActivity :
    AppCompatActivity(),
    NIDRegisterListener {
    private lateinit var binding: ActivityMainBinding
    private fun currentNavController(): NavController =
        findNavController(R.id.navHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onBackPressed() {
        when (currentNavController().currentDestination?.id) {
            R.id.fragmentRegisterTwo -> currentNavController().navigateUp()
            else -> finish()
        }
    }

    override fun goToNextScreen() {
        currentNavController().navigate(
            NIDRegisterFragmentOneDirections.actionGoToFragmentRegisterTwo()
        )
    }

    override fun goBackScreen() {
        currentNavController().navigateUp()
    }
}