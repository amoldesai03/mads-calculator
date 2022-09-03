package `in`.android.mads_calculator.utils

import `in`.android.mads_calculator.R
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


//View extenstions
fun View.toggleVisibility(show: Boolean = true) {
    visibility = if (show) View.VISIBLE else View.GONE
}


fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length)
    snack.setBackgroundTint(ContextCompat.getColor(context, R.color.blue))
    snack.setTextColor(Color.WHITE)
    snack.show()
}

//Mutable Map

fun MutableMap<String, String>.toHistory(): String {
    val sb = StringBuilder()
    var count = 1
    sb.append("************************ \n")
    forEach { k ->
        sb.append("$count. Expression => ${k.key} , Output => ${k.value} \n")
        count++
    }
    sb.append("************************ \n")
    return sb.toString()
}