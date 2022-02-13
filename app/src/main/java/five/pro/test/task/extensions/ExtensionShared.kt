package five.pro.test.task.extensions

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment

// get instance SharedPreferences
fun Fragment.getSharedPreferences(name: String) : SharedPreferences?{
    return this.activity?.getSharedPreferences(name, Context.MODE_PRIVATE)
}