package com.sample.neuroidapp.us

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neuroid.tracker.NeuroID
import com.sample.neuroidapp.us.databinding.FragmentRegisterTwoBinding
import com.sample.neuroidapp.us.listeners.NIDRegisterListener

class NIDRegisterFragmentTwo: Fragment() {
    private lateinit var binding : FragmentRegisterTwoBinding
    private var listener: NIDRegisterListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NIDRegisterListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterTwoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            NeuroID.getInstance().setScreenName("EMPLOYMENT_DETAILS")
            buttonAgreeAndCheck.setOnClickListener {
                NeuroID.getInstance().formSubmit()
            }
            buttonBack.setOnClickListener {
                listener?.goBackScreen()
            }
        }
    }
}