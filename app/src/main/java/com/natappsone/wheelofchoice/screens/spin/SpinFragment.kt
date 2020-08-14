package com.natappsone.wheelofchoice.screens.spin

import android.animation.Animator
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.database.WheelsDatabase
import com.natappsone.wheelofchoice.databinding.FragmentSpinBinding
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.screens.managewheel.ManageWheelViewModel
import com.natappsone.wheelofchoice.screens.managewheel.ManageWheelViewModelFactory
import kotlinx.android.synthetic.main.fragment_spin.*
import kotlinx.coroutines.awaitAll


/**
 * A simple [Fragment] subclass.
 * Use the [SpinFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SpinFragment : Fragment(){

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSpinBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_spin, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = WheelsDatabase.getInstance(application).wDao
        val vmFactory = ManageWheelViewModelFactory(dataSource, application)
        val vm = ViewModelProviders.of(this, vmFactory).get(ManageWheelViewModel::class.java)
        binding.lifecycleOwner = this

        val ll = binding.spinLl
        val btnSpin = binding.btnSpin
        val btnResult = binding.btnSpinResult
        var locWheels = listOf<Wheel>()
        var currentWheel = Wheel()

        vm.wheels.observe(viewLifecycleOwner, Observer {
            it.let { list ->
                list.forEach() { wheel ->

                    val tv = TextView(activity)
                    tv.text = wheel.wheelName
                    tv.tag = wheel.wheelId
                    tv.setTextAppearance(R.style.edit_text_label)
                    tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    tv.setOnClickListener{
                        currentWheel = wheel

                        btnSpin.isEnabled = true
                        btnSpin.isClickable = true

                    }
                    ll.addView(tv)
                }
                locWheels = list
            }
        })


        btnSpin.isEnabled = false
        btnSpin.isClickable = false
        btnSpin.setOnClickListener{
            currentWheel.wheelOptions.size.let { rand -> vm.spin(rand) }
        }

        vm.randomWheelOption.observe(viewLifecycleOwner, Observer {
            showResult(btnResult, currentWheel, it)
        })

        return binding.root
    }

    private fun showResult(btn: Button, wheel: Wheel, optionNb: Int){


        btn.startAnimation(
            AnimationUtils.loadAnimation(activity, R.anim.fade_in_while_rotate)
        )


        btn.text = wheel.wheelOptions[optionNb].wheelOptionName
        btn.setBackgroundColor(Color.parseColor(wheel.wheelOptions[optionNb].wheelOptionColor.toString()))

    }

}