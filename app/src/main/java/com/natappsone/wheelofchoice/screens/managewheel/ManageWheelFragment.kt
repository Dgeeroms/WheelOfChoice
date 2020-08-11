package com.natappsone.wheelofchoice.screens.managewheel

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.natappsone.wheelofchoice.R
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

        Log.i("ManageWheelFragment", "Called ViewModelProviders.of")
        vm = ViewModelProviders.of(this).get(ManageWheelViewModel::class.java)

        val binding: FragmentManageWheelBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_manage_wheel, container, false)
        return binding.root
    }

}