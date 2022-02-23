package five.pro.test.task.fragment_authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import five.pro.test.task.R
import five.pro.test.task.extensions.addText
import five.pro.test.task.extensions.getSharedPreferences
import five.pro.test.task.room_data_base.RoomUsersViewModel
@AndroidEntryPoint
class FragmentAuthorization : Fragment() {

// text fields
    private lateinit var loginPhone: EditText
    private lateinit var password: EditText
// button
    private lateinit var buttonContinue: Button
// progress bar
    private lateinit var progressBar: ProgressBar
// viewModels
    private val authorizationViewModel:AuthorizationViewModel by activityViewModels()
    private val roomViewModel: RoomUsersViewModel by viewModels()
// shared
    private val shared get() = this.getSharedPreferences(SharedKey.SHARED.name)
    private val logPh get() = this.shared?.getString(SharedKey.LOG.name, "").toString()
    private val pass get() = shared?.getString(SharedKey.PASS.name, "").toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)

// Initialization views
        loginPhone = view.findViewById(R.id.edit_phone)
        password = view.findViewById(R.id.edit_password)

        loginPhone.addText(logPh)
        password.addText(pass)

        buttonContinue = view.findViewById(R.id.button_continue)

        progressBar = view.findViewById(R.id.progress_bar)

        buttonContinue.setOnClickListener {
            // check network
            context?.let { authorizationViewModel.checkNetwork(it) }
            // check data user
            authorizationViewModel.checkDataUser( loginPhone = loginPhone.text.toString(),
                                                  password = password.text.toString(),
                                                  roomViewModel = roomViewModel,
                                                  fragment = this,
                                                  progressBar = progressBar)

            // save log pass
            authorizationViewModel.saveLogPass(shared = shared, logPh = loginPhone, pass = password)
        }

        return view

    }

}