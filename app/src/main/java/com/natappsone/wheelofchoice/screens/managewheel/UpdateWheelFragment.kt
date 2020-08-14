package com.natappsone.wheelofchoice.screens.managewheel

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.databinding.FragmentUpdateWheelBinding
import com.natappsone.wheelofchoice.models.Wheel
import kotlinx.android.synthetic.main.colorpicker.*
import kotlinx.android.synthetic.main.fragment_update_wheel.*
import kotlinx.android.synthetic.main.list_item_wheel_option.view.*


class UpdateWheelFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var args = arguments?.let { UpdateWheelFragmentArgs.fromBundle(it) }
        val binding: FragmentUpdateWheelBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_update_wheel, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = WheelsDatabase.getInstance(application).wDao
        val vmFactory = ManageWheelViewModelFactory(dataSource, application)
        val vm = ViewModelProviders.of(this, vmFactory).get(ManageWheelViewModel::class.java)

        binding.manageWheelViewModel = vm

        binding.lifecycleOwner = this

        vm.showSavedSnack.observe(viewLifecycleOwner, Observer {
            if(it == true){
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.updated_message),
                    Snackbar.LENGTH_SHORT
                ).show()

            }
        })

        args?.wheelKey?.let { vm.updateCurrentWheel(it) }

        vm.wheelToUpdate.observe(this, Observer {

            wheel ->
                wheel?.let{
                    binding.wheel = wheel
                }
        })



        val adapter = UpdateWheelsOptionListAdapter(WheelOptionsListListener {
                wheelOption ->
                    //if color is tapped, not implemented
                }
        )
        binding.wheelOptionsRecList.adapter = adapter
        vm.wheelToUpdate.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it.wheelOptions)
            }
        })




        return binding.root
    }


}