package com.natappsone.wheelofchoice.screens.managewheel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.databinding.FragmentManageWheelBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ManageWheelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ManageWheelFragment : Fragment() {

    private lateinit var vm: ManageWheelViewModel
    //private val

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentManageWheelBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_manage_wheel, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = WheelsDatabase.getInstance(application).wDao
        val vmFactory = ManageWheelViewModelFactory(dataSource, application)
        val vm = ViewModelProviders.of(this, vmFactory).get(ManageWheelViewModel::class.java)

        binding.manageWheelViewModel = vm

        binding.lifecycleOwner = this

        return binding.root
    }

}