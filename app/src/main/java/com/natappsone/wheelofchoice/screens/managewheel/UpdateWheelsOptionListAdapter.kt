package com.natappsone.wheelofchoice.screens.managewheel

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.natappsone.wheelofchoice.databinding.ListItemWheelBinding
import com.natappsone.wheelofchoice.databinding.ListItemWheelOptionBinding
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.models.WheelOption
import kotlinx.android.synthetic.main.colorpicker.*

class UpdateWheelsOptionListAdapter(val clickListener: WheelOptionsListListener) : ListAdapter<WheelOption, UpdateWheelsOptionListAdapter.ViewHolder>(WheelsOptionsDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding : ListItemWheelOptionBinding) :

        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: WheelOption, clickListener: WheelOptionsListListener) {
            binding.wheelOption = item
            binding.clickListener = clickListener
            binding.colorPickerUpdate.setOnClickListener{

            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemWheelOptionBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class WheelsOptionsDiffCallback : DiffUtil.ItemCallback<WheelOption>(){
    override fun areItemsTheSame(oldItem: WheelOption, newItem: WheelOption): Boolean {
        return  oldItem.wheelOptionName == newItem.wheelOptionName
    }

    override fun areContentsTheSame(oldItem: WheelOption, newItem: WheelOption): Boolean {
        return oldItem == newItem
    }
}

class WheelOptionsListListener(val clickListener: (option: WheelOption) -> Unit){
    fun onClick(wheelopt: WheelOption){
        return clickListener(wheelopt)
    }
}
/*
private fun initializeColorPicker(){


/*
        btnColorPicker.setOnClickListener {
            colorSelector.visibility = View.VISIBLE
        }
*/

    strColor.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable) {
            if (s.length == 6){
                colorA.progress = 255
                colorR.progress = Integer.parseInt(s.substring(0..1), 16)
                colorG.progress = Integer.parseInt(s.substring(2..3), 16)
                colorB.progress = Integer.parseInt(s.substring(4..5), 16)
            } else if (s.length == 8){
                colorA.progress = Integer.parseInt(s.substring(0..1), 16)
                colorR.progress = Integer.parseInt(s.substring(2..3), 16)
                colorG.progress = Integer.parseInt(s.substring(4..5), 16)
                colorB.progress = Integer.parseInt(s.substring(6..7), 16)
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int,
                                       count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {

        }
    })

    colorA.max = 255
    colorA.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                       fromUser: Boolean) {
            val colorStr = getColorString()
            strColor.setText(colorStr.replace("#","").toUpperCase())
            btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
        }
    })

    colorR.max = 255
    colorR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                       fromUser: Boolean) {
            val colorStr = getColorString()
            strColor.setText(colorStr.replace("#","").toUpperCase())
            btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
        }
    })

    colorG.max = 255
    colorG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                       fromUser: Boolean) {
            val colorStr = getColorString()
            strColor.setText(colorStr.replace("#","").toUpperCase())
            btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
        }
    })

    colorB.max = 255
    colorB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                       fromUser: Boolean) {
            val colorStr = getColorString()
            strColor.setText(colorStr.replace("#","").toUpperCase())
            btnColorPreview.setBackgroundColor(Color.parseColor(colorStr))
        }
    })

    colorCancelBtn.setOnClickListener {
        colorSelector.visibility = View.GONE
    }

    colorOkBtn.setOnClickListener {
        val color:String = getColorString()
        btnColorPicker.setBackgroundColor(Color.parseColor(color))
        colorSelector.visibility = View.GONE
    }
}

fun getColorString(): String {
    var a = Integer.toHexString(((255*colorA.progress)/colorA.max))
    if(a.length==1) a = "0"+a
    var r = Integer.toHexString(((255*colorR.progress)/colorR.max))
    if(r.length==1) r = "0"+r
    var g = Integer.toHexString(((255*colorG.progress)/colorG.max))
    if(g.length==1) g = "0"+g
    var b = Integer.toHexString(((255*colorB.progress)/colorB.max))
    if(b.length==1) b = "0"+b
    return "#" + a + r + g + b
}
*/


