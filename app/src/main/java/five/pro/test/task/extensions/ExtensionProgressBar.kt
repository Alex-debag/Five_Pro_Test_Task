package five.pro.test.task.extensions

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.start(motion: Boolean) {
    when(motion){
        true -> {
            this.visibility = View.VISIBLE
            this.isActivated = motion
        }
        false -> {
            this.visibility = View.GONE
            this.isActivated = motion
        }
    }
}