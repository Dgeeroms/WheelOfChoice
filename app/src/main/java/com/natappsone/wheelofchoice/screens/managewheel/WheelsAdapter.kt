package com.natappsone.wheelofchoice.screens.managewheel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.natappsone.wheelofchoice.R
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.utilities.converters.TextItemViewHolder

class WheelsAdapter : RecyclerView.Adapter<TextItemViewHolder>(){
    var data = listOf<Wheel>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data.get(position)
        holder.textView.text = item.wheelName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.wheel_item_view, parent, false) as TextView
        return  TextItemViewHolder(view)
    }

}