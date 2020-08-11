package com.natappsone.wheelofchoice.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "wheel_option_table")
@ForeignKey(entity = Wheel::class, parentColumns = ["wheelOptionId"], childColumns = ["wheelId"], onDelete = CASCADE)

            data class WheelOption(

        @PrimaryKey(autoGenerate = true)
        var wheelOptionId: Long = 0L,
        val wheelOptionName: String = "",
        val wheelOptionColor: String = "#ffffff"

    )