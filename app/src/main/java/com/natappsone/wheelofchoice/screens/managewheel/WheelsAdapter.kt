package com.natappsone.wheelofchoice.screens.managewheel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.natappsone.wheelofchoice.databinding.ListItemWheelBinding
import com.natappsone.wheelofchoice.databinding.ListItemWheelOptionBinding
import com.natappsone.wheelofchoice.models.Wheel


class WheelsAdapter(val clickListener: WheelListListener) : ListAdapter<Wheel, WheelsAdapter.ViewHolder>(WheelsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding : ListItemWheelBinding) :

        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Wheel, clickListener: WheelListListener? = null) {
            binding.wheel = item
            if(clickListener != null)
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemWheelBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}
class WheelsDiffCallback : DiffUtil.ItemCallback<Wheel>(){
    override fun areItemsTheSame(oldItem: Wheel, newItem: Wheel): Boolean {
        return  oldItem.wheelId == newItem.wheelId
    }

    override fun areContentsTheSame(oldItem: Wheel, newItem: Wheel): Boolean {
        return oldItem == newItem
    }
}

class WheelListListener(val clickListener: (wheelId: Long) -> Unit){
    fun onClick(wheel: Wheel) = clickListener(wheel.wheelId)
}



