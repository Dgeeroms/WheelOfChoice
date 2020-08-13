package com.natappsone.wheelofchoice.utilities

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.natappsone.wheelofchoice.models.Wheel
import java.lang.StringBuilder

fun formatWheels(wheels: List<Wheel>, resources: Resources): Spanned{
    val sb = StringBuilder()
    sb.apply {
        wheels.forEach {
            append("<br>")
            append(it.wheelName)
            //append("android:tag=\"" + it.wheelId + "\"")
            it.wheelOptions.forEach{
                append("<br>")
                append(it.wheelOptionName + " - " + it.wheelOptionColor)
            }
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}