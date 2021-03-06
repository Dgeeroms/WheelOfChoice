package com.natappsone.wheelofchoice.screens.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.databinding.FragmentTitleScreenBinding
/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleScreenBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_title_screen, container, false)

        binding.spinWheelButton.setOnClickListener(Navigation.createNavigateOnClickListener(
            R.id.action_titleScreenFragment_to_spinFragment
        ))
        binding.manageWheelsButton.setOnClickListener(Navigation.createNavigateOnClickListener(
            R.id.action_titleScreenFragment_to_manageWheelFragment
        ))
        binding.tutorialButton.setOnClickListener(Navigation.createNavigateOnClickListener(
            R.id.action_titleScreenFragment_to_tutorialFragment
        ))

        return binding.root
    }
}
