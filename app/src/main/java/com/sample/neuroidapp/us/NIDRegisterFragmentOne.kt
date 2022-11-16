package com.sample.neuroidapp.us

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.neuroid.tracker.NeuroID
import com.neuroid.tracker.utils.NIDVersion
import com.sample.neuroidapp.us.databinding.FragmentRegisterOneBinding
import com.sample.neuroidapp.us.extensions.getDays
import com.sample.neuroidapp.us.listeners.NIDRegisterListener
import com.sample.neuroidapp.us.utils.collect
import com.sample.neuroidapp.us.viewmodels.SandBoxViewModel

class NIDRegisterFragmentOne : Fragment() {
    private lateinit var binding: FragmentRegisterOneBinding
    private var listener: NIDRegisterListener? = null
    private val viewModel: SandBoxViewModel by activityViewModels()

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
            //[form_id] : [TEST/LIVE] : [clientID] : [tabId] - [First Timestamp in the session]
            val dynamoKey = NeuroID.getInstance()?.getSiteId() + " : " +
                    NeuroID.getInstance()?.getEnvironment() + " : " +
                    NeuroID.getInstance()?.getClientId() + " : " +
                    NeuroID.getInstance()?.getTabId() + " - " +
                    NeuroID.getInstance()?.getFirstTS()

            NeuroID.getInstance()?.setScreenName("PERSONAL_DETAILS")

            textViewUserId.text = NeuroID.getInstance()?.getUserId()
            textViewClientId.text = dynamoKey

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

            dobMonth.apply {
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

            buttonReset.setOnClickListener {
                textViewClientId.text = ""
                textViewScore.text = "0.0"
                viewModel.restartApp()
                textViewUserId.text = NeuroID.getInstance()?.getUserId()
                val dynamoKeyReset = NeuroID.getInstance()?.getSiteId() + " : " +
                        NeuroID.getInstance()?.getEnvironment() + " : " +
                        NeuroID.getInstance()?.getClientId() + " : " +
                        NeuroID.getInstance()?.getTabId() + " - " +
                        NeuroID.getInstance()?.getFirstTS()

                textViewClientId.text = dynamoKeyReset

            }
            buttonCloseSession.setOnClickListener {
                viewModel.closeSessionAndCheckScore()
            }

            tvSDKVersion.text =
                NIDVersion.getInternalCurrentVersion() + " (" + BuildConfig.BUILD_TYPE + ")"

            collect(viewModel.error) {
                // No op
            }
            collect(viewModel.score) { scores ->
                textViewScore.text = (scores.firstOrNull()?.score ?: 0.0).toString()
            }
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