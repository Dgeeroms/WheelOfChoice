package com.natappsone.wheelofchoice.screens.managewheel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.databinding.FragmentNewWheelBinding
import com.natappsone.wheelofchoice.databinding.FragmentTitleScreenBinding


class NewWheelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewWheelBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_new_wheel, container, false)

        binding.newSaveButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_newWheelFragment_to_manageWheelFragment
            ))

        return binding.root
    }


}