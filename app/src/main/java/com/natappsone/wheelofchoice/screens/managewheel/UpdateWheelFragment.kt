package com.natappsone.wheelofchoice.screens.managewheel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.databinding.FragmentUpdateWheelBinding


class UpdateWheelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUpdateWheelBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_update_wheel, container, false)

        binding.updateSaveButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_updateWheelFragment_to_manageWheelFragment
            ))

        return binding.root
    }

}