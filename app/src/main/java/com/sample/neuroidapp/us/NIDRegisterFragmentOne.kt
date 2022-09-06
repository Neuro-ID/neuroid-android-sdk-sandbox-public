package com.sample.neuroidapp.us

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.neuroid.tracker.NeuroID
import com.neuroid.tracker.utils.NIDVersion
import com.sample.neuroidapp.us.databinding.FragmentRegisterOneBinding
import com.sample.neuroidapp.us.extensions.getDays
import com.sample.neuroidapp.us.listeners.NIDRegisterListener

class NIDRegisterFragmentOne : Fragment() {
    private lateinit var binding: FragmentRegisterOneBinding
    private var listener: NIDRegisterListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NIDRegisterListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterOneBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val sessionId = NeuroID.getInstance()?.getSessionId()
            val clientId = NeuroID.getInstance()?.getClientId()
            NeuroID.getInstance()?.setScreenName("PERSONAL_DETAILS")
            textViewSessionId.text = sessionId
            textViewClientId.text = clientId

            val yearList = resources.getStringArray(R.array.nid_app_array_years)
            val monthList = resources.getStringArray(R.array.nid_app_array_months)

            val adapterYear = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                yearList
            )

            editTextBirthYear.apply {
                setAdapter(adapterYear)
            }

            val adapterMonth = ArrayAdapter(
                requireContext(),
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

            buttonContinue.setOnClickListener {
                listener?.goToNextScreen()
            }

            tvSDKVersion.text =
                NIDVersion.getInternalCurrentVersion() + " (" + BuildConfig.BUILD_TYPE + ")"
        }
    }

    private fun getAdapter(position: Int): ArrayAdapter<Int> {
        return ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            getDays(position)
        )
    }
}