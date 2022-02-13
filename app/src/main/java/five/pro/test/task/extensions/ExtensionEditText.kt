package five.pro.test.task.extensions

import android.text.Editable
import android.widget.EditText

fun EditText.addText(text: String){
    this.text = Editable.Factory.getInstance().newEditable(text)
}