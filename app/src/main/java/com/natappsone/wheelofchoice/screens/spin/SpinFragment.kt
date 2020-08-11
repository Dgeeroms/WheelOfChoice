package com.natappsone.wheelofchoice.screens.spin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.databinding.FragmentSpinBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SpinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSpinBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_spin, container, false)
        return binding.root
    }

}