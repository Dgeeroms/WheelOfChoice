package com.natappsone.wheelofchoice.screens.managewheel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.databinding.FragmentUpdateWheelBinding


class UpdateWheelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUpdateWheelBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_update_wheel, container, false)

        /* to be replace by Observer + onclick listener
        binding.updateSaveButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_updateWheelFragment_to_manageWheelFragment
            )
        )

        */

        val application = requireNotNull(this.activity).application
        val dataSource = WheelsDatabase.getInstance(application).wDao
        val vmFactory = ManageWheelViewModelFactory(dataSource, application)
        val vm = ViewModelProviders.of(this, vmFactory).get(ManageWheelViewModel::class.java)

        binding.manageWheelViewModel = vm

        binding.lifecycleOwner = this

        vm.showSavedSnack.observe(this, Observer {
            if(it == true){
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.updated_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                vm.doneNavigating()
            }
        })

        return binding.root
    }

}