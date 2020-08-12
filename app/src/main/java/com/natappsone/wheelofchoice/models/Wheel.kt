package com.natappsone.wheelofchoice.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wheel_table")
data class Wheel(

    @PrimaryKey(autoGenerate = true)
    var wheelId: Long = 0L,
    @ColumnInfo(name = "wheel_name")
    var wheelName: String = "",
    var wheelOptions: MutableList<WheelOption> = ArrayList()

)