package five.pro.test.task.fragment_authorization

import android.content.Context
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import five.pro.test.task.MainActivity
import five.pro.test.task.R
import five.pro.test.task.extensions.getBody
import five.pro.test.task.extensions.isNetworkAvailable
import five.pro.test.task.extensions.start
import five.pro.test.task.extensions.toast
import five.pro.test.task.parser_json.AuthorizationGetDataUser
import five.pro.test.task.parser_json.AuthorizationModel
import five.pro.test.task.parser_json.ParserJsonViewModel
import five.pro.test.task.parser_json.TypeRequest
import five.pro.test.task.room_data_base.ModelDataUser
import five.pro.test.task.room_data_base.RoomUsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val CHECK_DATA_URL = "https://tips-api-staging.crio-server.com/login"

class AuthorizationViewModel : ViewModel(){

    val userId: MutableLiveData<Int> by lazy { MutableLiveData() }

// checks the correctness of the data
    fun checkDataUser(loginPhone: String, password: String, roomViewModel: RoomUsersViewModel,
                      fragment: Fragment?, progressBar: ProgressBar) {

        progressBar.start(true)

        when {
            ( password.isNotEmpty() && loginPhone.length >= 11) -> {

                val body = AuthorizationModel( phone_code = getCode(loginPhone),
                                               phone_number = getPhone(loginPhone),
                                               password = password).getBody()

                ParserJsonViewModel().getResponse(AuthorizationGetDataUser(), CHECK_DATA_URL,
                    TypeRequest.post, body){

                    viewModelScope.launch(Dispatchers.Main) { progressBar.start(false) }

                    checkResponse(it, roomViewModel, fragment)
                }
            }
            else -> {
                progressBar.start(false)
                fragment?.context.toast("Check your login and password")
            }
        }
    }

// check response
    private fun checkResponse(data: AuthorizationGetDataUser?,
                      roomViewModel: RoomUsersViewModel, fragment: Fragment?){
        when (data) {
            null -> viewModelScope.launch(Dispatchers.Main) {

                fragment?.context.toast("Check your login and password")
            }
            else -> { viewModelScope.launch(Dispatchers.IO) {

                insertDataUsers(roomUsersViewModel = roomViewModel, data = data)
            }
                viewModelScope.launch(Dispatchers.Main) { navigation( activity = fragment?.activity,
                    id = R.id.action_fragmentAuthorization_to_fragmentUserData) }
            }
        }
    }

// save data user in database
    private suspend fun insertDataUsers(roomUsersViewModel: RoomUsersViewModel,
                                        data: AuthorizationGetDataUser?) {
        val it = data?.user

        val dataModel = it?.user_id?.let { id ->
            ModelDataUser( user_id =  id,
                           phone_number = it.phone_code.toString() + it.phone_number.toString(),
                           name = it.name.toString(),
                           second_name = it.second_name.toString() )
        }

        withContext(Dispatchers.IO) {
            dataModel?.let { data -> roomUsersViewModel.insert(data) }
        }

        data?.user?.user_id?.let { id -> userId.postValue(id) }
    }

// for get code phone number
    private fun getCode(loginPhone: String) : String?{

       return try {

           val suffix = loginPhone.substring(loginPhone.length - 9, loginPhone.length)
           loginPhone.removeSuffix(suffix)

       }catch (e: Exception){

            println("ERROR Code")
            null
        }
    }
// for get phone
    private fun getPhone(loginPhone: String) : String?{

        return try {

            loginPhone.substring(loginPhone.length - 9, loginPhone.length)

        }catch (e: Exception){

            println("ERROR Phone")
            null
        }
    }
// check network
    fun checkNetwork(context: Context) {
        if (context.isNetworkAvailable()){
            println("Network connection successful")
        }else{
            context.toast("Your network connection stopped, check your network")
        }
    }
// navigation fun
    private fun navigation(activity: FragmentActivity?, id: Int){
        (activity as MainActivity).navController.navigate(id)
    }
// save log and pass in settings
    fun saveLogPass(shared: SharedPreferences?, logPh: EditText?, pass: EditText?) {
        shared?.edit().apply{
            this?.putString(SharedKey.LOG.name, logPh?.text.toString())
            this?.putString(SharedKey.PASS.name, pass?.text.toString())
        }?.apply()
    }
}
// key for SharedPreferences
enum class SharedKey{
    SHARED, LOG, PASS
}