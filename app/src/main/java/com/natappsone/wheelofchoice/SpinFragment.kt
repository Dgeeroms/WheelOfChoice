package com.natappsone.wheelofchoice

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val binding: FragmentSpinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_spin, container, false)
        return binding.root
    }

}