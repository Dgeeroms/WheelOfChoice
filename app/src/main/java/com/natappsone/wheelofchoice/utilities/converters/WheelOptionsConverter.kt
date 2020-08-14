package com.natappsone.wheelofchoice.utilities.converters

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.TypeConverter
import com.natappsone.wheelofchoice.models.WheelOption
import java.util.*
import kotlin.collections.ArrayList

class WheelOptionsConverter {
    @TypeConverter
    fun storedStringToOptions(value: String): MutableList<WheelOption> {
        val opts: Array<String> = value.split("\\s*;\\s*".toRegex()).toTypedArray()

        val optionList: MutableList<WheelOption> = ArrayList()
        for(opt in opts){
            if(opt == ""){
                continue
            }
            val splitOpt: Array<String> = opt.split("\\s*,\\s*".toRegex()).toTypedArray()
            val optObj = WheelOption()

            optObj.wheelOptionName = splitOpt[0]
            optObj.wheelOptionColor = splitOpt[1]
            optionList.add(optObj)
        }
        return optionList
    }

    @TypeConverter
    fun optionsToStoredString(wo: MutableList<WheelOption>): String {
        var value = ""
        for (opt in wo){
            value += "${opt.wheelOptionName},${opt.wheelOptionColor};"
        }

        return value
    }
}

