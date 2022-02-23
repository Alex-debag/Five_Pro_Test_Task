package five.pro.test.task.fragment_user_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import five.pro.test.task.R
import five.pro.test.task.fragment_authorization.AuthorizationViewModel
import five.pro.test.task.room_data_base.RoomUsersViewModel
@AndroidEntryPoint
class FragmentUserData: Fragment() {

// text fields
    private lateinit var userSurname: TextView
    private lateinit var userName: TextView
    private lateinit var userPhone: TextView
// view models
    private val authorizationViewModel:AuthorizationViewModel  by activityViewModels()
    private val userDataViewModel by viewModels<UserDataViewModel>()
    private val roomViewModel: RoomUsersViewModel by viewModels()
//    private val roomViewModel: RoomUsersViewModel by viewModels {
//        WordViewModelFactory((activity?.application as ThisApplication).repository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_data, container, false)

        // init
        userSurname = view.findViewById(R.id.user_surname_text)
        userName = view.findViewById(R.id.user_name_text)
        userPhone = view.findViewById(R.id.user_phone_text)
// observe all users
        roomViewModel.allUsers.observe(activity as LifecycleOwner){ listUsers ->
            // fill this form
            userDataViewModel.getUser( listUsers = listUsers,
                                       id = authorizationViewModel.userId.value){ i ->
                userSurname.text = i?.second_name
                userName.text = i?.name
                userPhone.text = i?.phone_number
            }
        }

        return view
    }

}



fun main(){

}
