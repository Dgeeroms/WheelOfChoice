package com.natappsone.wheelofchoice.screens.managewheel

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.natappsone.wheelofchoice.models.Wheel
import com.natappsone.wheelofchoice.models.WheelOption

@BindingAdapter("wheelNameText")
fun TextView.setWheelNameText(item: Wheel?){
    item?.let {
        text = item.wheelName
    }
}


@BindingAdapter("optionNameText")
fun TextView.setOptionNameText(item: WheelOption?){
    item?.let {
        text = item.wheelOptionName
    }
}

@SuppressLint("Range")
@BindingAdapter("optionColor")
fun Button.setColorText(item: WheelOption?){
    item?.let {
        setBackgroundColor(Color.parseColor(item.wheelOptionColor))
    }
}