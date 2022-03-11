package com.sample.neuroidapp.us

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.neuroid.tracker.NeuroID
import com.sample.neuroidapp.us.databinding.ActivityMainBinding
import com.sample.neuroidapp.us.extensions.getDays

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            val sessionId = NeuroID.getInstance().getSessionId()
            textViewSessionId.text = sessionId

            val yearList = resources.getStringArray(R.array.nid_app_array_years)
            val monthList = resources.getStringArray(R.array.nid_app_array_months)

            val adapterYear =  ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_dropdown_item_1line,
                yearList
            )

            editTextBirthYear.apply {
                setAdapter(adapterYear)
            }

            val adapterMonth =  ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_dropdown_item_1line,
                monthList
            )

            editTextBirthMonth.apply {
                setAdapter(adapterMonth)
                setOnItemClickListener { _, _, position, _ ->
                    editTextBirthDay.setText("")
                    editTextBirthDay.isEnabled = true
                    editTextBirthDay.setAdapter(getAdapter(position))
                }
            }

            buttonAgreeAndCheck.setOnClickListener {
                NeuroID.getInstance().formSubmit()
            }
        }

    }

    private fun getAdapter(position: Int): ArrayAdapter<Int> {
        return  ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_dropdown_item_1line,
            getDays(position)
        )
    }
}