package com.natappsone.wheelofchoice.screens.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.databinding.FragmentTutorialBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TutorialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorialFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTutorialBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_tutorial, container, false)
        return binding.root
    }

}