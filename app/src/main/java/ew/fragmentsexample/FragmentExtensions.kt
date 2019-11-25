package ew.fragmentsexample

import android.util.Log
import androidx.fragment.app.Fragment

fun Fragment.log(message: String) {
    Log.i("TAG: " + this.javaClass.name, message)
}