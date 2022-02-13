package five.pro.test.task.fragment_user_data

import androidx.lifecycle.ViewModel
import five.pro.test.task.room_data_base.ModelDataUser

class UserDataViewModel : ViewModel() {
    // get data user
    fun getUser(listUsers: List<ModelDataUser>, id: Int?, res: (ModelDataUser?) -> (Unit)){

        dat@for (i in listUsers){

            when(i.user_id){
                id -> {
                    res(i)
                    break@dat
                }
                else -> continue@dat
            }
        }
    }
}