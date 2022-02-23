package five.pro.test.task.room_data_base

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomUsersViewModel @Inject constructor( private val repository: UsersRepository ) : ViewModel() {

        val allUsers: LiveData<List<ModelDataUser>> = repository.allUsers.asLiveData()
// add user on background thread
        fun insert(user: ModelDataUser) = viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    class WordViewModelFactory(private val repository: UsersRepository) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(RoomUsersViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RoomUsersViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }