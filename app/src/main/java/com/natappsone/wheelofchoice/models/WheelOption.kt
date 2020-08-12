package com.natappsone.wheelofchoice.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "wheel_options_table")
data class WheelOption(

    //@PrimaryKey(autoGenerate = true)
    //var wheelOptionId: Long = 0L,
    var wheelOptionName: String = "",
    var wheelOptionColor: String = ""

    )